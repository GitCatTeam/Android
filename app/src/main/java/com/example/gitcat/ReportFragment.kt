package com.example.gitcat

import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import kotlinx.android.synthetic.main.fragment_report.*
import kotlinx.android.synthetic.main.fragment_report.view.*

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

class ReportFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment

        val rootView = inflater.inflate(R.layout.fragment_report,container,false)
        val report_recyclerview = rootView.findViewById(R.id.report_recyclerview) as RecyclerView
        report_recyclerview.layoutManager = LinearLayoutManager(activity)

        val listAdapter = ReportAdapter(activity!!)
        listAdapter.data = listOf(
            Report("1회","Java"),
            Report("2회","HTML")
        )

        report_recyclerview.adapter = listAdapter
        Log.e("에러","ㅇ레러ㅐ어랴어랠ㅇㄹㄴㄹㅇㅇㅇㅇㅇㅇㅇㅇㅇㅇㅇㅇㅇㅇㅇㅇㅇㅇㅇㅇㅇㅇㅇㅇㅇㅇㅇㅇㅇㅇㅇㅇㅇㅇㅇㅇㅇㅇㅇ")


        listAdapter.notifyDataSetChanged()

        return rootView
    }

    companion object {
        fun newInstance(): ReportFragment = ReportFragment()
    }
}
