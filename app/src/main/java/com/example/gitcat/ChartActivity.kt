package com.example.gitcat

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
import com.example.gitcat.R
import com.github.mikephil.charting.components.Legend
import androidx.core.app.ComponentActivity
import androidx.core.app.ComponentActivity.ExtraData
import androidx.core.content.ContextCompat.getSystemService
import android.icu.lang.UCharacter.GraphemeClusterBreak.T
import com.github.mikephil.charting.formatter.PercentFormatter


class ChartActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_chart)

        lineChart()
        pieChart()
        barChart()


    }

    private fun lineChart(){
        lineChart.description.isEnabled = false
        lineChart.setExtraOffsets(5F,10F, 5F,5F)

        lineChart.dragDecelerationFrictionCoef = 0.95F

        val entry = ArrayList<Entry>()

        entry.add(Entry(1F,1F))
        entry.add(Entry(2F,2F))
        entry.add(Entry(3F,0F))
        entry.add(Entry(4F,4F))
        entry.add(Entry(5F,3F))

        lineChart.animateY(1000, Easing.EaseInOutCubic)

        val dataSet = LineDataSet(entry, "Countries")
        dataSet.lineWidth = 2F
        dataSet.circleRadius = 6F
        dataSet.setCircleColor(Color.parseColor("#8acbf6"))
        dataSet.circleHoleColor = Color.BLUE
        dataSet.color = Color.parseColor("#8acbf6")
        dataSet.setDrawCircleHole(true)
        dataSet.setDrawCircles(true)
        dataSet.setDrawHorizontalHighlightIndicator(false)
        dataSet.setDrawHighlightIndicators(false)
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

        lineChart.animateY(2000,Easing.EaseInOutCubic)
        lineChart.invalidate()
    }

    private fun pieChart(){
        pieChart.setUsePercentValues(true)
        pieChart.description.isEnabled = false
        //pieChart.setExtraOffsets(5F,10F, 5F,5F)

        pieChart.dragDecelerationFrictionCoef = 0.95F

        pieChart.isDrawHoleEnabled = true
        pieChart.setHoleColor(Color.WHITE)
        pieChart.transparentCircleRadius = 40F

        val entry = ArrayList<PieEntry>()

        entry.add(PieEntry(34F,"Korea"))
        entry.add(PieEntry(23F,"USA"))
        entry.add(PieEntry(14F,"UK"))
        entry.add(PieEntry(18F,"Russia"))

        val legend = Legend()
        legend.isEnabled = true
        legend.form = Legend.LegendForm.CIRCLE
        legend.verticalAlignment = Legend.LegendVerticalAlignment.BOTTOM
        legend.horizontalAlignment = Legend.LegendHorizontalAlignment.LEFT
        legend.orientation = Legend.LegendOrientation.VERTICAL
        legend.setDrawInside(false)

        pieChart.animateY(1000, Easing.EaseInOutCubic)

        val dataSet = PieDataSet(entry, "Countries")
        dataSet.sliceSpace = 3F
        dataSet.selectionShift = 5F
        dataSet.setColors(Color.parseColor("#8acbf6"),Color.parseColor("#ccebff"),Color.parseColor("#f2faff"),Color.parseColor("#eeeeee"))

        val data = PieData((dataSet))
        //data.setValueTextSize(10F)
        //data.setValueTextColor(Color.YELLOW)
        data.setValueFormatter(PercentFormatter())
        pieChart.data = data



    }

    private fun barChart(){
        val dataSet = BarDataSet(getData(),"Countries")
        dataSet.barBorderWidth = 0.9F
        dataSet.setColors(Color.parseColor("#ccebff"),Color.parseColor("#8acbf6"),Color.parseColor("#f2faff"))

        //dataSet.color = ColorTemplate.COLORFUL_COLORS

        val data = BarData(dataSet)
        data.setValueFormatter(PercentFormatter())
        val xAxis = barChart.xAxis
        xAxis.setDrawAxisLine(false)
        xAxis.setDrawGridLines(false)
        val leftAxis = barChart.axisLeft
        val rightAxis = barChart.axisRight
        leftAxis.isEnabled = false
        rightAxis.isEnabled = false

        xAxis.position = XAxis.XAxisPosition.BOTTOM
        xAxis.granularity = 1F

        val months = arrayOf("레파1", "레파2", "레파3")
        val formatter = IndexAxisValueFormatter(months)
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

    private fun getData(): ArrayList<BarEntry> {
        val entries = ArrayList<BarEntry>()
        entries.add(BarEntry(0f, 30f))
        entries.add(BarEntry(1f, 80f))
        entries.add(BarEntry(2f, 60f))
        return entries
    }
}
