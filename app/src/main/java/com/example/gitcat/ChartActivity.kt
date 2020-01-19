package com.example.gitcat

import android.graphics.Color
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_chart.*
import com.github.mikephil.charting.animation.Easing
import com.github.mikephil.charting.components.Description
import com.github.mikephil.charting.components.XAxis
import com.github.mikephil.charting.data.*
import com.github.mikephil.charting.formatter.IndexAxisValueFormatter
import com.github.mikephil.charting.data.BarEntry

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
        dataSet.setCircleColor(Color.parseColor("#FFA1B4DC"))
        dataSet.circleHoleColor = Color.BLUE
        dataSet.color = Color.parseColor("#FFA1B4DC")
        dataSet.setDrawCircleHole(true)
        dataSet.setDrawCircles(true)
        dataSet.setDrawHorizontalHighlightIndicator(false)
        dataSet.setDrawHighlightIndicators(false)
        dataSet.setDrawValues(false)

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
        pieChart.setExtraOffsets(5F,10F, 5F,5F)

        pieChart.dragDecelerationFrictionCoef = 0.95F

        pieChart.isDrawHoleEnabled = false
        pieChart.setHoleColor(Color.WHITE)
        pieChart.transparentCircleRadius = 61F

        val entry = ArrayList<PieEntry>()

        entry.add(PieEntry(34F,"Korea"))
        entry.add(PieEntry(23F,"USA"))
        entry.add(PieEntry(14F,"UK"))
        entry.add(PieEntry(18F,"Russia"))
        entry.add(PieEntry(9F,"India"))


        pieChart.animateY(1000, Easing.EaseInOutCubic)

        val dataSet = PieDataSet(entry, "Countries")
        dataSet.sliceSpace = 3F
        dataSet.selectionShift = 5F
        //dataSet.colors = ColorTemplate.JOYFUL_COLORS

        val data = PieData((dataSet))
        data.setValueTextSize(10F)
        data.setValueTextColor(Color.YELLOW)

        pieChart.data = data
    }

    private fun barChart(){
        val dataSet = BarDataSet(getData(),"Countries")
        dataSet.barBorderWidth = 0.9F
        //dataSet.color = ColorTemplate.COLORFUL_COLORS

        val data = BarData(dataSet)
        val xAxis = barChart.xAxis
        xAxis.position = XAxis.XAxisPosition.BOTTOM
        xAxis.granularity = 1F

        val months = arrayOf("Jan", "Feb", "Mar", "Apr", "May", "Jun")
        val formatter = IndexAxisValueFormatter(months)
        xAxis.valueFormatter = formatter

        val description = Description()
        description.text = ""
        barChart.description = description

        barChart.data = data
        barChart.setFitBars(true)
        barChart.animateY(1000,Easing.EaseInOutCubic)
        barChart.invalidate()
    }

    private fun getData(): ArrayList<BarEntry> {
        val entries = ArrayList<BarEntry>()
        entries.add(BarEntry(0f, 30f))
        entries.add(BarEntry(1f, 80f))
        entries.add(BarEntry(2f, 60f))
        entries.add(BarEntry(3f, 50f))
        entries.add(BarEntry(4f, 70f))
        entries.add(BarEntry(5f, 60f))
        return entries
    }
}
