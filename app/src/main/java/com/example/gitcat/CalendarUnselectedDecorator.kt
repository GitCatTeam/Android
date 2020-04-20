package com.example.gitcat

import android.app.Activity
import android.graphics.drawable.Drawable
import com.prolificinteractive.materialcalendarview.CalendarDay
import com.prolificinteractive.materialcalendarview.DayViewDecorator
import com.prolificinteractive.materialcalendarview.DayViewFacade

class CalendarUnselectedDecorator : DayViewDecorator{
    private lateinit var drawable: Drawable
    private var dates: CalendarDay? = null

    constructor(dates: CalendarDay, context: Activity){
        this.dates = dates
        drawable = context.getDrawable(R.drawable.calendar_unselect)
    }

    override fun shouldDecorate(day: CalendarDay?): Boolean {
        return dates != null && day!!.equals(dates)
    }

    override fun decorate(view: DayViewFacade?) {
        view!!.setBackgroundDrawable(drawable)
    }
}