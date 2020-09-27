package com.catlove.gitcat

import android.graphics.*
import android.graphics.drawable.GradientDrawable
import android.widget.SeekBar
import android.graphics.Shader
import android.graphics.LinearGradient
import android.graphics.drawable.Drawable
import android.util.Log.d
import android.graphics.PixelFormat
import android.graphics.ColorFilter
import android.graphics.RectF
import android.graphics.Paint.ANTI_ALIAS_FLAG
import android.graphics.Color.parseColor
import android.opengl.ETC1.getHeight
import android.opengl.ETC1.getWidth




class SeekBarDrawable(parts: Int) : Drawable() {

    private var parts = 10

    private var paint: Paint? = null
    private val fillColor = Color.parseColor("#88cdf6")
    private val emptyColor = Color.parseColor("#ebebeb")
    private val separatorColor = Color.parseColor("#FFFFFF")
    private var rectFill: RectF? = null
    private var rectEmpty: RectF? = null
    private var separators: MutableList<RectF>? = null

    init {
        this.parts = parts
        this.paint = Paint(Paint.ANTI_ALIAS_FLAG)
        this.separators = ArrayList()
    }

    override fun onLevelChange(level: Int): Boolean {
        invalidateSelf()
        return true
    }

    override fun draw(canvas: Canvas) {
        // Calculate values
        val b = bounds
        val width = b.width()
        val height = b.height()

        val spaceFilled = level * width / 10000
        this.rectFill = RectF(0f, 0f, spaceFilled.toFloat(), height.toFloat())
        this.rectEmpty = RectF(spaceFilled.toFloat(), 0f, width.toFloat(), height.toFloat())

        val spaceBetween = width / 100
        val widthPart = width / this.parts - (0.9 * spaceBetween).toInt()
        var startX = widthPart

        for (i in 0 until this.parts - 1) {
            val rectF = RectF(
                startX.toFloat(),
                0f,
                (startX + spaceBetween).toFloat(),
                height.toFloat()
            )
            this.separators!!.add(rectF)
            startX += spaceBetween + widthPart
        }

        val radius = 9
        // Foreground
        this.paint!!.color = this.fillColor
        canvas.drawRoundRect(
            this.rectFill!!, // rect
            radius.toFloat(), // rx
            radius.toFloat(), // ry
            this.paint!! // Paint
        )
        //canvas.drawRect(this.rectFill!!, this.paint!!)

        // Background
        this.paint!!.color = this.emptyColor
        canvas.drawRoundRect(
            this.rectEmpty!!, // rect
            radius.toFloat(), // rx
            radius.toFloat(), // ry
            this.paint!! // Paint
        )
        //canvas.drawRect(this.rectEmpty!!, this.paint!!)

        // Separator
        this.paint!!.color = this.separatorColor
        for (separator in this.separators!!) {
            canvas.drawRoundRect(
                separator, // rect
                radius.toFloat(), // rx
                radius.toFloat(), // ry
                this.paint!! // Paint
            )
            //canvas.drawRect(separator, this.paint!!)
        }
    }

    override fun setAlpha(alpha: Int) {}

    override fun setColorFilter(cf: ColorFilter?) {}

    override fun getOpacity(): Int {
        return PixelFormat.TRANSLUCENT
    }
}

//class SeekBarDrawable(progress: String) : Drawable() {
//    private val NUM_SEGMENTS = 4
//    private var mForegroundStart: Int = Color.parseColor("#e6f5ff")
//    private var mForegroundEnd: Int = Color.parseColor("#88cdf6")
//    private var mBackground: Int = Color.parseColor("#ebebeb")
//    private val mPaint = Paint()
//    private val mSegment = RectF()
//    private val progress = progress
//
//    override fun onLevelChange(level: Int): Boolean {
//        invalidateSelf()
//        return true
//    }
//
//    override fun draw(canvas: Canvas) {
//        val level = level / 10000f
//        val b : Rect = bounds
//        val gapWidth = b.height() / 2f
//        val segmentWidth = (b.width() - (NUM_SEGMENTS - 1) * gapWidth) / NUM_SEGMENTS
//
//        mSegment.set(0f, 0f, segmentWidth, b.height().toFloat())
//        mPaint.setShader(
//            LinearGradient(
//                0f,
//                0f,
//                b.width().toFloat(),
//                0f,
//                mForegroundStart,
//                mForegroundEnd,
//                Shader.TileMode.CLAMP
//            )
//        )
//
//        d("*+*+",progress)
//        for (i in 0 until NUM_SEGMENTS) {
//            val loLevel = i / NUM_SEGMENTS
//            val hiLevel = (i + 1) / NUM_SEGMENTS
//            if (loLevel <= level && level <= hiLevel) {
//                val middle = mSegment.left + NUM_SEGMENTS * segmentWidth * (level - loLevel)
//
//                canvas.drawRect(mSegment.left, mSegment.top, middle, mSegment.bottom, mPaint)
//                mPaint.color = mBackground
//                canvas.drawRect(middle, mSegment.top, mSegment.right, mSegment.bottom, mPaint)
//            } else {
//                canvas.drawRect(mSegment, mPaint)
//            }
//            mSegment.offset(mSegment.width() + gapWidth, 0f)
//        }
//
//    }
//
//    override fun setAlpha(p0: Int) {
//    }
//
//    override fun getOpacity(): Int {
//        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
//    }
//
//    override fun setColorFilter(p0: ColorFilter?) {
//    }
//
//}