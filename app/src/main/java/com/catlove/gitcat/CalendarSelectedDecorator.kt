package com.catlove.gitcat

import android.app.Activity
import android.graphics.Color
import android.graphics.drawable.Drawable
import android.text.style.ForegroundColorSpan
import com.prolificinteractive.materialcalendarview.CalendarDay
import com.prolificinteractive.materialcalendarview.DayViewDecorator
import com.prolificinteractive.materialcalendarview.DayViewFacade

class CalendarSelectedDecorator : DayViewDecorator {
    private lateinit var drawable: Drawable
    private var dates: CalendarDay? = null

    constructor(dates: CalendarDay, context: Activity){
        this.dates = dates
        drawable = context.getDrawable(R.drawable.calendar_select)!!
    }

    override fun shouldDecorate(day: CalendarDay?): Boolean {
        return dates != null && day!!.equals(dates)
    }

    override fun decorate(view: DayViewFacade?) {
        //view!!.setSelectionDrawable(drawable)
        view!!.setBackgroundDrawable(drawable)
        view!!.addSpan(ForegroundColorSpan(Color.BLACK))
        //view!!.addSpan(DotSpan(5f, color))
    }




}