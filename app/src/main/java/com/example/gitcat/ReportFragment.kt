package com.example.gitcat

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.fragment_report.*
import kotlinx.android.synthetic.main.fragment_report.view.*

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

class ReportFragment : Fragment() {

    var reportList = arrayListOf<Report>(
        Report("1회","Java"),
        Report("2회","HTML")
    )

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment

        val rootView = inflater.inflate(R.layout.fragment_report,container,false)
        val report_recyclerview = rootView.findViewById(R.id.report_recyclerview) as RecyclerView
        report_recyclerview.layoutManager = LinearLayoutManager(activity)

//        val listAdapter = ReportAdapter(activity!!)
//        listAdapter.data = listOf(
//            Report("1회","Java"),
//            Report("2회","HTML")
//        )

        val listAdapter = ReportAdapter(activity!!,reportList){report ->
            val intent = Intent(context, ChartActivity::class.java)
            intent.putExtra("commit", report.totalCommit)
            intent.putExtra("lang",report.language)
            //intent.putExtra("completeMessage", message)
            //intent.putExtra("main","main")
            startActivity(intent)
        }

        report_recyclerview.adapter = listAdapter
        Log.e("에러","ㅇ레러ㅐ어랴어랠ㅇㄹㄴㄹㅇㅇㅇㅇㅇㅇㅇㅇㅇㅇㅇㅇㅇㅇㅇㅇㅇㅇㅇㅇㅇㅇㅇㅇㅇㅇㅇㅇㅇㅇㅇㅇㅇㅇㅇㅇㅇㅇㅇ")


        listAdapter.notifyDataSetChanged()

        return rootView
    }

    companion object {
        fun newInstance(): ReportFragment = ReportFragment()
    }

//    private fun startToChartActivity(id: String, message: String) {
//        val intent = Intent(context, ChartActivity::class.java)
//        intent.putExtra("id", id)
//        intent.putExtra("completeMessage", message)
//        intent.putExtra("main","main")
//        startActivity(intent)
//    }
}
