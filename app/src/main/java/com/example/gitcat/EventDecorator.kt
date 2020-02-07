package com.example.gitcat

import android.app.Activity
import android.graphics.drawable.Drawable
import android.os.AsyncTask
import com.prolificinteractive.materialcalendarview.CalendarDay
import com.prolificinteractive.materialcalendarview.DayViewDecorator
import com.prolificinteractive.materialcalendarview.DayViewFacade
import com.prolificinteractive.materialcalendarview.spans.DotSpan

class EventDecorator : DayViewDecorator {

    private lateinit var drawable: Drawable
    private var color: Int = 0
    private var dates: HashSet<CalendarDay>? = null

    constructor(color: Int, dates: List<CalendarDay>, context: Activity){
        drawable = context.getResources().getDrawable(R.drawable.calendar_select)
        this.color = color
        this.dates = HashSet(dates)
    }

    override fun shouldDecorate(day: CalendarDay?): Boolean {
        return dates!!.contains(day)
    }

    override fun decorate(view: DayViewFacade?) {
        view!!.setSelectionDrawable(drawable)
        //view!!.addSpan(DotSpan(5f, color))
    }
}