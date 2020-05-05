package com.example.gitcat

import android.content.SharedPreferences
import android.graphics.Color
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_chart.*
import com.github.mikephil.charting.animation.Easing
import com.github.mikephil.charting.components.Description
import com.github.mikephil.charting.components.XAxis
import com.github.mikephil.charting.data.*
import com.github.mikephil.charting.formatter.IndexAxisValueFormatter
import com.github.mikephil.charting.data.BarEntry
import android.util.Log
import android.util.Log.d
import android.view.View
import com.example.gitcat.model.MonthlyDetailModel
import com.example.gitcat.retrofit.RetrofitCreator
import com.github.mikephil.charting.components.Legend
import com.github.mikephil.charting.formatter.PercentFormatter
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class ChartActivity : AppCompatActivity() {

    val lineEntry = ArrayList<Entry>()
    val barMonth = arrayListOf<String>()
    val barEntries = ArrayList<BarEntry>()
    val pieEntry = ArrayList<PieEntry>()
    val pieLegendName = ArrayList<String>()
    val pieLegendPercent = ArrayList<Float>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_chart)

        backButton.setOnClickListener {
            onBackPressed()
        }

        val settings: SharedPreferences = getSharedPreferences("gitcat", MODE_PRIVATE)

        /*API*/
        val id: Int
        id=intent.getStringExtra("id").toInt()
        chartTitle.text = intent.getStringExtra("title")
        totalCommit.text = intent.getStringExtra("commit")

        NewToken(this)
        val call: Call<MonthlyDetailModel> = RetrofitCreator.service.getMonthlyDetail(settings.getString("token",""),id)
        call.enqueue(
            object : Callback<MonthlyDetailModel> {
                override fun onFailure(call: Call<MonthlyDetailModel>, t: Throwable) {
                    Log.e("*+*+", "error: $t")
                    showErrorPopup(t.toString(),this@ChartActivity)
                }

                override fun onResponse(
                    call: Call<MonthlyDetailModel>,
                    response: Response<MonthlyDetailModel>
                ) {
                    if(response.isSuccessful){
                        val reportData = response.body()!!

                        //상단 텍스트
                        lastMonth.text = reportData.data.comparedLastMonth
                        avg.text = reportData.data.avgCount

                        //LineChart
                        var c: Float = 1F
                        for(line in reportData.data.dailyCount.countArray){
                            lineEntry.add(Entry(c,line.toFloat()))
                            c++
                        }
                        lineChart(lineEntry)

                        //PieChart
                        for(pie in reportData.data.languageRatio.resultLanguages){
                            pieEntry.add(PieEntry(pie.percent,pie.language))
                            pieLegendName.add(pie.language)
                            pieLegendPercent.add(pie.percent)
                        }
                        pieChart(pieEntry)
                        //pieChart legend 처리
                        val pieCount = pieLegendName.size
                        if(pieCount.equals(1)){
                            secondlegend.visibility = View.GONE
                            thirdlegend.visibility = View.GONE
                            fourthlegend.visibility = View.GONE

                            firstlegend_name.text = pieLegendName[0]
                            firstlegend_percent.text = pieLegendPercent[0].toString()+"%"
                        }else if(pieCount.equals(2)){
                            thirdlegend.visibility = View.GONE
                            fourthlegend.visibility = View.GONE

                            firstlegend_name.text = pieLegendName[0]
                            firstlegend_percent.text = pieLegendPercent[0].toString()+"%"
                            secondlegend_name.text = pieLegendName[1]
                            secondlegend_percent.text = pieLegendPercent[1].toString()+"%"
                        }else if(pieCount.equals(3)){
                            fourthlegend.visibility = View.GONE

                            firstlegend_name.text = pieLegendName[0]
                            firstlegend_percent.text = pieLegendPercent[0].toString()+"%"
                            secondlegend_name.text = pieLegendName[1]
                            secondlegend_percent.text = pieLegendPercent[1].toString()+"%"
                            thirdlegend_name.text = pieLegendName[2]
                            thirdlegend_percent.text = pieLegendPercent[2].toString()+"%"
                        }else{
                            firstlegend_name.text = pieLegendName[0]
                            firstlegend_percent.text = pieLegendPercent[0].toString()+"%"
                            secondlegend_name.text = pieLegendName[1]
                            secondlegend_percent.text = pieLegendPercent[1].toString()+"%"
                            thirdlegend_name.text = pieLegendName[2]
                            thirdlegend_percent.text = pieLegendPercent[2].toString()+"%"
                            fourthlegend_name.text = pieLegendName[3]
                            fourthlegend_percent.text = pieLegendPercent[3].toString()+"%"
                        }

                        //BarChart
                        for(names in reportData.data.contributedRepository.repoNames){
                            barMonth.add(names)
                        }
                        var count: Float = 0F
                        for(value in reportData.data.contributedRepository.count){
                            barEntries.add(BarEntry(count,value))
                            count++
                        }
                        barChart(barMonth,barEntries)

                        //세줄평
                        three_text1.text = reportData.data.comment[0]
                        three_text2.text = reportData.data.comment[1]
                        three_text3.text = reportData.data.comment[2]
                    }
                    else{
                        showErrorPopup("["+response.code().toString()+"] "+response.message(),this@ChartActivity)
                    }
                }
            }
        )

    }

    override fun onDestroy() {
        super.onDestroy()
    }

    private fun lineChart(lineEntry: ArrayList<Entry>) {
        lineChart.description.isEnabled = false
        lineChart.setExtraOffsets(5F,10F, 5F,5F)

        lineChart.dragDecelerationFrictionCoef = 0.95F

        lineChart.animateY(1000, Easing.EaseInOutCubic)

        val dataSet = LineDataSet(lineEntry, "")
        dataSet.lineWidth = 1F
        dataSet.circleRadius = 2F
        dataSet.setCircleColor(Color.parseColor("#8acbf6"))
        dataSet.circleHoleColor = Color.parseColor("#8acbf6")
        dataSet.color = Color.parseColor("#8acbf6")
        dataSet.setDrawCircleHole(true)
        dataSet.setDrawCircles(true)
        dataSet.setDrawHorizontalHighlightIndicator(true)
        dataSet.setDrawHighlightIndicators(true)
        dataSet.setDrawValues(false)
        dataSet.setDrawFilled(true)
        dataSet.fillColor = Color.parseColor("#8acbf6")

        val data = LineData((dataSet))
        //안썼음 data.setValueTextSize(10F)
        //data.setValueTextColor(Color.YELLOW)

        lineChart.data = data

        val xAxis = lineChart.xAxis
        xAxis.position = XAxis.XAxisPosition.BOTTOM
        xAxis.setTextColor(Color.BLACK)
        xAxis.enableGridDashedLine(8F, 24F, 0F)

        val yLAxis = lineChart.axisLeft
        //오류뜸 yLAxis.textColor = COLOR.BLACK

        val yRAxis = lineChart.axisRight
        yRAxis.setDrawLabels(false)
        yRAxis.setDrawAxisLine(false)
        yRAxis.setDrawGridLines(false)

        lineChart.isDoubleTapToZoomEnabled = false
        lineChart.setDrawGridBackground(false)
        lineChart.setTouchEnabled(false)
        lineChart.animateY(2000,Easing.EaseInOutCubic)
        lineChart.invalidate()
    }

    private fun pieChart(pieEntry: ArrayList<PieEntry>) {
        pieChart.setUsePercentValues(true)
        pieChart.description.isEnabled = false
        //pieChart.setExtraOffsets(5F,10F, 5F,5F)

        //pieChart.dragDecelerationFrictionCoef = 0.95F 애니메이션

        pieChart.isDrawHoleEnabled = true
        pieChart.setHoleColor(Color.WHITE)
        pieChart.transparentCircleRadius = 40F
        pieChart.centerText = "언어비율\n(%)"
        pieChart.setCenterTextSize(10F)
        pieChart.setDrawCenterText(true)
        pieChart.setDrawSliceText(false)

        val legend = pieChart.legend
        legend.isEnabled = false
        //legend.form = Legend.LegendForm.CIRCLE
        //legend.verticalAlignment = Legend.LegendVerticalAlignment.CENTER
        //legend.horizontalAlignment = Legend.LegendHorizontalAlignment.LEFT
        //legend.orientation = Legend.LegendOrientation.VERTICAL
        legend.setDrawInside(false)

        pieChart.animateY(1000, Easing.EaseInOutCubic)

        val dataSet = PieDataSet(pieEntry, "")
        dataSet.sliceSpace = 3F
        dataSet.selectionShift = 5F
        dataSet.setColors(Color.parseColor("#8acbf6"),Color.parseColor("#ccebff"),Color.parseColor("#f2faff"),Color.parseColor("#eeeeee"))

        val data = PieData((dataSet))
        data.setValueTextSize(10F)
        //data.setValueTextColor(Color.YELLOW)
        data.setValueFormatter(PercentFormatter())
        dataSet.xValuePosition= PieDataSet.ValuePosition.OUTSIDE_SLICE
        dataSet.yValuePosition = PieDataSet.ValuePosition.OUTSIDE_SLICE
        pieChart.data = data

    }

    private fun barChart(barMonth: List<String>, barEntries: ArrayList<BarEntry>){
        val dataSet = BarDataSet(barEntries,"")
        dataSet.barBorderWidth = 0.9F
        dataSet.setColors(Color.parseColor("#8acbf6"),Color.parseColor("#ccebff"),Color.parseColor("#f2faff"))

        //dataSet.color = ColorTemplate.COLORFUL_COLORS

        val data = BarData(dataSet)
//        data.setValueFormatter(PercentFormatter())
        val xAxis = barChart.xAxis
        xAxis.setDrawAxisLine(false)
        xAxis.setDrawGridLines(false)
        val leftAxis = barChart.axisLeft
        val rightAxis = barChart.axisRight
        leftAxis.isEnabled = false
        rightAxis.isEnabled = false

        xAxis.position = XAxis.XAxisPosition.BOTTOM
        xAxis.granularity = 1F

        val formatter = IndexAxisValueFormatter(barMonth)
        xAxis.valueFormatter = formatter

        val description = Description()
        description.text = ""
        barChart.description = description

        barChart.data = data
        barChart.setFitBars(true)
        barChart.setTouchEnabled(false)
        barChart.animateY(1000,Easing.EaseInOutCubic)
        barChart.invalidate()
    }

}
