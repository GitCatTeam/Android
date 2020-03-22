package com.example.gitcat

import android.app.Activity
import android.graphics.Color
import android.os.AsyncTask
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import android.util.Log
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import kotlin.collections.ArrayList
import android.widget.Toast
import com.example.gitcat.model.MonthCommitContentModel
import com.example.gitcat.model.MonthCommitCountModel
import com.example.gitcat.retrofit.GithubAPI
import com.example.gitcat.retrofit.RetrofitCreator
import com.prolificinteractive.materialcalendarview.CalendarDay
import com.prolificinteractive.materialcalendarview.MaterialCalendarView
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers
import kotlinx.android.synthetic.main.fragment_calendar.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"
//var calendarView: MaterialCalendarView? = null

class CalendarFragment: Fragment() {

    val dates = ArrayList<CalendarDay>()

    var repoList = arrayListOf<Repository>()

    lateinit var compositeDisposable: CompositeDisposable

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment

        val rootView = inflater.inflate(R.layout.fragment_calendar, container, false)
        val calendarView = rootView.findViewById<MaterialCalendarView>(R.id.calendarView)
        val repository_recyclerview = rootView.findViewById(R.id.repository_recyclerview) as RecyclerView
        var apimonth : String = ""
        var calendarDay: CalendarDay = CalendarDay.today()
        var ymToday : String
        if(calendarDay.month>9){
            ymToday = calendarDay.year.toString()+calendarDay.month.toString()
        }
        else{
            ymToday = calendarDay.year.toString()+"0"+calendarDay.month.toString()
        }
        /*FIXME: Token 수정*/
        APIStart(calendarView,"token",ymToday)

        calendarView?.setOnDateChangedListener { widget, date, selected ->

            val Year = date.year.toString()
            val Month = date.month.toString()
            val Day = date.day.toString()
            var dates : String = Year

            if(date.month<10){
                dates += "0"+Month
                if(date.day<10){
                    dates += "0"+Day
                }
                else{
                    dates += Day
                }
            }else{
                dates += Month
                if(date.day<10){
                    dates += "0"+Day
                }
                else{
                    dates += Day
                }
            }
            //TODO: 누른 날짜만 선택되기
            //calendarView.removeDecorator(CalendarSelectedDecorator(calendarDay,activity!!))
            //calendarView.addDecorator(CalendarSelectedDecorator(date,activity!!))

            /*FIXME: Token 수정*/
            APIContent("token",dates)

            calendarView?.clearSelection()

        }
        calendarView?.setOnMonthChangedListener { widget, date ->
            val mYear = date.year
            val mMonth = date.month
            val mDay = date.day

            Log.i("Move date", mYear.toString() + "")
            Log.i("Move date", mMonth.toString() + "")
            Log.i("Move date", mDay.toString() + "")

            if(mMonth>9){
                apimonth = mYear.toString()+mMonth.toString()
            }else{
                apimonth = mYear.toString()+"0"+mMonth.toString()
            }

            /*FIXME: Token 수정*/
            APIStart(calendarView,"token",apimonth)
        }

        return rootView
    }

    /*달력 API*/
    fun APIStart(calendarView:MaterialCalendarView, token:String, date:String){

        val call: Call<MonthCommitCountModel> = RetrofitCreator.service.getMonthCommitCount(token,date)
        call.enqueue(
            object : Callback<MonthCommitCountModel> {
                override fun onFailure(call: Call<MonthCommitCountModel>, t: Throwable) {
                    Log.e("*+*+", "error: $t")
                }

                override fun onResponse(
                    call: Call<MonthCommitCountModel>,
                    response: Response<MonthCommitCountModel>
                ) {
                    if(response.isSuccessful){
                        val month = response.body()!!

                        APIFlow(month.data.commits.data1,"level_1")
                        calendarView.addDecorator(EventDecorator(dates,activity!!,"level_1"))
                        dates.clear()
                        APIFlow(month.data.commits.data2,"level_2")
                        calendarView.addDecorator(EventDecorator(dates,activity!!,"level_2"))
                        dates.clear()
                        APIFlow(month.data.commits.data3,"level_3")
                        calendarView.addDecorator(EventDecorator(dates,activity!!,"level_3"))
                        dates.clear()
                    }
                }
            }
        )
    }

    /*날짜 계산*/
    fun APIFlow(array: ArrayList<String>, level: String){
        for(date in array){

            var ymd:List<String> = date.split("-")
            if(ymd[1].toInt()<10){
                var m = ymd[1].substring(1).toInt()
                if(ymd[2].toInt()<10){//9월9일
                    var d = ymd[1].substring(1).toInt()
                    dates.add(CalendarDay.from(ymd[0].toInt(),m,d))
                }else{//9월10일
                    dates.add(CalendarDay.from(ymd[0].toInt(),m,ymd[2].toInt()))
                }
            }else{
                if(ymd[2].toInt()<10){//10월9일
                    var d = ymd[1].substring(1).toInt()
                    dates.add(CalendarDay.from(ymd[0].toInt(),ymd[1].toInt(),d))
                }else{//10월10일
                    dates.add(CalendarDay.from(ymd[0].toInt(),ymd[1].toInt(),ymd[2].toInt()))
                }
            }
        }//for문 끝
    }

    /*달력 상세 API*/
    fun APIContent(token: String, dates:String){

        var repoName: String = ""
        var time: String
        var message: String

        val call: Call<MonthCommitContentModel> = RetrofitCreator.service.getMonthCommitContent(token,dates)
        call.enqueue(
            object : Callback<MonthCommitContentModel> {
                override fun onFailure(call: Call<MonthCommitContentModel>, t: Throwable) {
                    commitLayout.visibility = View.GONE
                    noCommitText.visibility = View.VISIBLE
                    noCommitText.text = "오류가 발생하였습니다. 관리자에게 문의해주세요!"
                }

                override fun onResponse(
                    call: Call<MonthCommitContentModel>,
                    response: Response<MonthCommitContentModel>
                ) {
                    if(response.isSuccessful){
                        commitLayout.visibility = View.VISIBLE
                        noCommitText.visibility = View.GONE

                        val date = response.body()!!

                        if(date.data.commits.isEmpty()){//커밋이 없는 날
                            commitLayout.visibility = View.GONE
                            noCommitText.visibility = View.VISIBLE
                        }else{
                            repoList.clear()
                            commitLayout.visibility = View.VISIBLE
                            noCommitText.visibility = View.GONE

                            commit_score.text = date.data.score
                            commit_totalCommit.text = date.data.totalCommit
                            commit_item.text = date.data.item

                            for(data in date.data.commits){
                                var commitList= arrayListOf<RepositoryDetail>()
                                repoName = data.repoName
                                //commit = data.commit
                                for(commit in data.commit){
                                    time = commit.time
                                    message = commit.message
                                    commitList.add(RepositoryDetail(time,message))
                                }
                                repoList.add(Repository(repoName,commitList))
                            }

                            //여기서부터 RecyclerView
                            repository_recyclerview.layoutManager = LinearLayoutManager(activity)
                            val listAdapter = RepositoryAdapter(activity!!,repoList)
                            repository_recyclerview.adapter = listAdapter
                            listAdapter.notifyDataSetChanged()
                        }//commit 비어있지 않을 때(else)
                    }//response success end
                }
            }
        )
    }

    companion object {
        fun newInstance(): CalendarFragment = CalendarFragment()
    }
}

