package com.weilun.components.icon

import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import android.graphics.drawable.ShapeDrawable
import android.graphics.drawable.shapes.OvalShape
import android.util.Log
import java.lang.Math.min

class TextOvalDrawable(private val builder: Builder) : ShapeDrawable(OvalShape()) {
    private val textPaint: Paint by lazy { Paint() }

    init {
        textPaint.apply {
            color = builder.textColor
            isAntiAlias = true
            style = Paint.Style.FILL
            textAlign = Paint.Align.CENTER
        }

        paint.color = builder.backgroundColor
        Log.d("BaoTest", "Init : ${builder.backgroundColor}")
    }

    override fun draw(canvas: Canvas) {
        super.draw(canvas)
        Log.d("BaoTest", "DrawStart")
        val count = canvas.save()
        canvas.translate(bounds.left.toFloat(), bounds.top.toFloat())
        val width = if (builder.width < 0) bounds.width() else builder.width
        val height = if (builder.height < 0) bounds.height() else builder.height
        val fontSize = min(width, height) / 2

        textPaint.textSize = fontSize.toFloat()

        canvas.drawText(
            builder.text[0].toString(),
            (width / 2).toFloat(),
            height / 2 - (textPaint.descent() + textPaint.ascent()) / 2,
            textPaint
        )

        canvas.restoreToCount(count)

        Log.d("BaoTest", "Draw")
    }

    override fun getIntrinsicWidth(): Int {
        return builder.width
    }

    override fun getIntrinsicHeight(): Int {
        return builder.height
    }

    class Builder {
        var text: String = ""
        var backgroundColor: Int = Color.GRAY
        var textColor: Int = Color.GRAY
        var width: Int = -1
        var height: Int = -1

        fun backgroundColor(color: Int): Builder {
            this.backgroundColor = backgroundColor
            return this
        }

        fun textColor(color: Int): Builder {
            this.textColor = color
            return this
        }

        fun width(width: Int): Builder {
            this.width = width
            return this
        }

        fun height(height: Int): Builder {
            this.height = height
            return this
        }

        fun build(text: String): TextOvalDrawable {
            this.text = text
            return TextOvalDrawable(this)
        }
    }
}