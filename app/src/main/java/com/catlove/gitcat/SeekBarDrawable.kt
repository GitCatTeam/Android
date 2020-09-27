package com.catlove.gitcat

import android.graphics.*
import android.graphics.drawable.GradientDrawable
import android.widget.SeekBar
import android.graphics.Shader
import android.graphics.LinearGradient
import android.graphics.drawable.Drawable


class SeekBarDrawable : Drawable() {
    private val NUM_SEGMENTS = 4
    private var mForegroundStart: Int = Color.parseColor("#e6f5ff")
    private var mForegroundEnd: Int = Color.parseColor("#88cdf6")
    private var mBackground: Int = Color.parseColor("#ebebeb")
    private val mPaint = Paint()
    private val mSegment = RectF()

    override fun draw(canvas: Canvas) {
        val level = level / 10000f
        val b : Rect = bounds
        val gapWidth = b.height() / 2f
        val segmentWidth = (b.width() - (NUM_SEGMENTS - 1) * gapWidth) / NUM_SEGMENTS
        mSegment.set(0f, 0f, segmentWidth, b.height().toFloat())

        mPaint.setShader(
            LinearGradient(
                0f,
                0f,
                b.width().toFloat(),
                0f,
                mForegroundStart,
                mForegroundEnd,
                Shader.TileMode.CLAMP
            )
        )

        for (i in 0 until NUM_SEGMENTS) {
            val loLevel = i / NUM_SEGMENTS
            val hiLevel = (i + 1) / NUM_SEGMENTS
            if (loLevel <= level && level <= hiLevel) {
                val middle = mSegment.left + NUM_SEGMENTS * segmentWidth * (level - loLevel)
                canvas.drawRect(mSegment.left, mSegment.top, middle, mSegment.bottom, mPaint)
                mPaint.color = mBackground
                canvas.drawRect(middle, mSegment.top, mSegment.right, mSegment.bottom, mPaint)
            } else {
                canvas.drawRect(mSegment, mPaint)
            }
            mSegment.offset(mSegment.width() + gapWidth, 0f)
        }

    }

    override fun setAlpha(p0: Int) {
    }

    override fun getOpacity(): Int {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun setColorFilter(p0: ColorFilter?) {
    }

}