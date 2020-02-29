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
import android.util.Log.d
import android.util.Log.e
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import java.text.SimpleDateFormat
import java.time.LocalDate
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter
import java.util.*
import java.util.concurrent.Executors
import kotlin.collections.ArrayList
import android.widget.Toast
import com.example.gitcat.model.MonthCommitCountModel
import com.example.gitcat.retrofit.GithubAPI
import com.example.gitcat.retrofit.RetrofitCreator
import com.prolificinteractive.materialcalendarview.CalendarDay
import com.prolificinteractive.materialcalendarview.MaterialCalendarView
import com.prolificinteractive.materialcalendarview.OnDateSelectedListener
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response


// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"
//var calendarView: MaterialCalendarView? = null

class CalendarFragment: Fragment() {

    val dates = ArrayList<CalendarDay>()

    var repoList = arrayListOf<Repository>(
        Repository("레포지토리1"),
        Repository("레포지토리2")
    )
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
        APIStart(calendarView,"eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJpc3MiOiJHaXRDYXQiLCJzdWIiOiJ5ZWppOTE3NSIsImlhdCI6MTU4MjY5ODA3MTk0NiwiZXhwIjoxNTgyNzg0NDcxOTQ2fQ.v6vUTmcT3EQblA2sU8oe8kBYnNc0srCHeNtuQSspUmI",ymToday)

        calendarView?.setOnDateChangedListener { widget, date, selected ->
            val Year = date.year
            val Month = date.month
            val Day = date.day

            Log.i("Now date", Year.toString() + "")
            Log.i("Now date", Month.toString() + "")
            Log.i("Now date", Day.toString() + "")

            val shot_Day = "$Year,$Month,$Day"

            Log.i("shot_Day test", shot_Day + "")
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

            APIStart(calendarView,"eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJpc3MiOiJHaXRDYXQiLCJzdWIiOiJ5ZWppOTE3NSIsImlhdCI6MTU4MjY5ODA3MTk0NiwiZXhwIjoxNTgyNzg0NDcxOTQ2fQ.v6vUTmcT3EQblA2sU8oe8kBYnNc0srCHeNtuQSspUmI",apimonth)
        }


        //FIXME: 여기서부터 RecyclerView
        repository_recyclerview.layoutManager = LinearLayoutManager(activity)
        val listAdapter = RepositoryAdapter(activity!!,repoList)
        repository_recyclerview.adapter = listAdapter
        listAdapter.notifyDataSetChanged()

        return rootView
    }

    /*API*/
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
                        d("*+*+", month.data.commits.toString())

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

    companion object {
        fun newInstance(): CalendarFragment = CalendarFragment()
    }
}

//private class ApiSimulator : AsyncTask<Void, Void, List<CalendarDay>> {
//
//
//    lateinit var Time_Result: Array<String>
//
//    constructor(Time_Result: Array<String>) {
//        this.Time_Result = Time_Result
//    }
//
//    override fun doInBackground(vararg p0: Void?): List<CalendarDay> {
//        try {
//            Thread.sleep(500)
//        } catch (e: InterruptedException) {
//            e.printStackTrace()
//        }
//
//        val calendar = Calendar.getInstance()
//        val dates = ArrayList<CalendarDay>()
//
//        /*특정날짜 달력에 점표시해주는곳*/
//        /*월은 0이 1월 년,일은 그대로*/
//        //string 문자열인 Time_Result 을 받아와서 ,를 기준으로짜르고 string을 int 로 변환
//
//        for (i in 0 until Time_Result.size) {
//            val day = CalendarDay.today()
//            val time = Time_Result[i].split(",")
//            val year = Integer.parseInt(time[0])
//            val month = Integer.parseInt(time[1])
//            val dayy = Integer.parseInt(time[2])
//
//            dates.add(day)
//            calendar.set(year, month - 1, dayy)
//        }
//
//        return dates
//    }
//
//    override fun onPostExecute(calendarDays: List<CalendarDay>) {
//        super.onPostExecute(calendarDays)
//
////        if (isFinishing()) {
////            return
////        }
//        calendarView!!.addDecorator(
//            EventDecorator(
//                Color.GREEN,
//                calendarDays,
//                Activity()
//            )
//        )
//    }
//
//}
//

