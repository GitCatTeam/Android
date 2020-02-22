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
import com.prolificinteractive.materialcalendarview.CalendarDay
import com.prolificinteractive.materialcalendarview.MaterialCalendarView
import com.prolificinteractive.materialcalendarview.OnDateSelectedListener
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers


// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"
//var calendarView: MaterialCalendarView? = null

class CalendarFragment: Fragment() {

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

        calendarView?.setOnDateChangedListener(OnDateSelectedListener { widget, date, selected ->
            val Year = date.year
            val Month = date.month
            val Day = date.day

            Log.i("Year test", Year.toString() + "")
            Log.i("Month test", Month.toString() + "")
            Log.i("Day test", Day.toString() + "")

            val shot_Day = "$Year,$Month,$Day"

            Log.i("shot_Day test", shot_Day + "")
            calendarView?.clearSelection()

        })

        /*API*/
        compositeDisposable = CompositeDisposable()
        compositeDisposable.add(
            GithubAPI.getMonthCommitCount("202002")
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.newThread())
                .subscribe({ response: MonthCommitCountModel ->
                    for (item in response.data) {
                        //TODO: 여러 층으로 된 JSON 풀어가기
                        //Log.d("Chart", (item.commits.).toString())
                    }
                }, { error: Throwable ->
                    Log.d("Chart", error.localizedMessage)
                    Toast.makeText(activity!!, "Error ${error.localizedMessage}", Toast.LENGTH_SHORT).show()
                }))

        val dates = ArrayList<CalendarDay>()
        val level:String = ""//level_1, level_2

        dates.add(CalendarDay.from(2020,2,5))
        dates.add(CalendarDay.from(2020,2,7))
        calendarView.addDecorator(EventDecorator(dates,activity!!,"level_2"))


        //ApiSimulator(result).executeOnExecutor(Executors.newSingleThreadExecutor())
        //ApiSimulator(result).execute()
//        Log.i("오류오류","************************************************")

        //FIXME: 여기서부터 RecyclerView
        repository_recyclerview.layoutManager = LinearLayoutManager(activity)
        val listAdapter = RepositoryAdapter(activity!!,repoList)
        repository_recyclerview.adapter = listAdapter
        listAdapter.notifyDataSetChanged()

        return rootView
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

