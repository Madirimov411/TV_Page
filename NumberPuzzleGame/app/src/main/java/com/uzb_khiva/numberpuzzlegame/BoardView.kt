package com.uzb_khiva.numberpuzzlegame

import android.content.Context
import android.graphics.Canvas
import android.graphics.Paint
import android.view.MotionEvent
import android.view.View

class BoardView(
    context: Context?,
    private val board: Board
) : View(context) {

    private var width = 0f
    private var height = 0f
    override fun onSizeChanged(w: Int, h: Int, oldw: Int, oldh: Int) {

        width = (w / board.size).toFloat()
        height = (h / board.size).toFloat()

        super.onSizeChanged(w, h, oldw, oldh)
    }

    private fun localPlace(x: Float, y: Float): Place {
        val ix = (x / width).toInt()
        val iY = (y / height).toInt()
        return board.at(ix + 1, iY + 1)!!
    }

    override fun onTouchEvent(event: MotionEvent?): Boolean {
        if (event!!.action == MotionEvent.ACTION_BUTTON_PRESS) return super.onTouchEvent(event)
        val p = localPlace(event.x, event.y)
        if (p != null && p.slidable() && !board.solved()) {
            p.slide()
            invalidate()
        }
        return true
    }

    override fun onDraw(canvas: Canvas) {

        val background = Paint()
        background.color = resources.getColor(R.color.board_color)
        canvas.drawRect(0f, 0f, getWidth().toFloat(), getHeight().toFloat(), background)

        val dark = Paint()
        dark.color = resources.getColor(R.color.tile_color2)
        dark.strokeWidth = 15f

        val dark1 = Paint()
        dark.color = resources.getColor(R.color.tile_color2)
        dark.strokeWidth = 15f

        //Draw the major grid lines
        for (i in 1..board.size) {
            canvas.drawLine(0f, (i * height), getWidth().toFloat(), (i * height), dark1)
            canvas.drawLine((i * width), 0f, (i * width), getHeight().toFloat(), dark1)
        }

        val foreground = Paint(Paint.ANTI_ALIAS_FLAG)
        foreground.color = resources.getColor(R.color.tile_color)
        foreground.style = Paint.Style.FILL
        foreground.textSize = height * 0.75f
        foreground.textAlign = Paint.Align.CENTER
        foreground.textScaleX = width / height
        val x = width / 2
        val fm = foreground.fontMetrics
        val y = height / 2 - (fm.ascent + fm.descent) / 2
        val it = board.places().iterator()
        for (i in 0 until board.size()) {
            for (j in 0 until board.size()) {
                if (it.hasNext()) {
                    val p = it.next()
                    if (p.hasTile()) {
                        val number = Integer.toString(p.tile!!.number()!!)
                        canvas.drawText(number, x + (i * width), y + (j * height), foreground)
                    } else {
                        canvas.drawRect(
                            (i * width),
                            (j * height),
                            (i * width) + width,
                            (j * height) + height,
                            dark
                        )
                    }
                }
            }

        }
    }

    init {
        isFocusable = true
        isFocusableInTouchMode = true
    }

}