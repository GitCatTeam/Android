package com.example.gitcat

import android.content.Intent
import android.content.SharedPreferences
import android.os.Bundle
import android.util.Log
import android.util.Log.d
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.gitcat.model.MonthlyListModel
import com.example.gitcat.retrofit.RetrofitCreator
import kotlinx.android.synthetic.main.fragment_report.*
import kotlinx.android.synthetic.main.fragment_report.view.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

class ReportFragment : Fragment() {

    var reportList = arrayListOf<Report>()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment

        val rootView = inflater.inflate(R.layout.fragment_report,container,false)
        val report_recyclerview = rootView.findViewById(R.id.report_recyclerview) as RecyclerView
        report_recyclerview.layoutManager = LinearLayoutManager(activity)
        val settings: SharedPreferences = requireActivity().getSharedPreferences("gitcat",
            AppCompatActivity.MODE_PRIVATE)

        //API

        var rId: String
        var rTitle: String
        var rTotalCommit: String
        var rLanguage: String

        NewToken(context!!)
        val call: Call<MonthlyListModel> = RetrofitCreator.service.getMonthlyList(settings.getString("token","")!!)
        call.enqueue(
            object : Callback<MonthlyListModel> {
                override fun onFailure(call: Call<MonthlyListModel>, t: Throwable) {
                    Log.e("*+*+", "error: $t")
                    showErrorPopup("재로그인을 해주세요!",activity!!)
                }

                override fun onResponse(
                    call: Call<MonthlyListModel>,
                    response: Response<MonthlyListModel>
                ) {
                    if(response.isSuccessful){
                        val reportData = response.body()!!

                        if(reportData.data.resultList.isEmpty()){
                            noReport.visibility = View.VISIBLE
                            report_scroll.visibility = View.GONE
                        }else{
                            noReport.visibility = View.GONE
                            report_scroll.visibility = View.VISIBLE

                            for(data in reportData.data.resultList){
                                rId = data.id.toString()
                                rTitle = data.title
                                rTotalCommit = data.totalCount
                                rLanguage = data.mainLanguage
                                reportList.add(Report(rId,rTitle,rTotalCommit,rLanguage))
                            }

                            val listAdapter = ReportAdapter(activity!!,reportList){report ->
                                val intent = Intent(context, ChartActivity::class.java)
                                intent.putExtra("id",report.id)
                                intent.putExtra("title",report.title)
                                intent.putExtra("commit", report.totalCommit)

                                startActivity(intent)
                            }

                            report_recyclerview.adapter = listAdapter

                            listAdapter.notifyDataSetChanged()
                        }//데이터 들어있을 때 end

                    }else{
                        if(response.code()>=500){
                            showErrorPopup("[네트워크 오류] 재로그인을 해주세요!",activity!!)
                        }else{
                            showErrorPopup("["+response.code().toString()+" 오류] "+"내부 서버 오류",activity!!)
                        }
                    }
                }
            }
        )

//        val listAdapter = ReportAdapter(activity!!)
//        listAdapter.data = listOf(
//            Report("1회","Java"),
//            Report("2회","HTML")
//        )
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
