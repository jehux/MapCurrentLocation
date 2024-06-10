package com.example.mapcurrentlocation.customviews

import android.content.Context
import android.graphics.Canvas
import android.graphics.Paint
import android.graphics.Path
import android.util.AttributeSet
import android.view.View
import com.example.mapcurrentlocation.R

class WaveView(context: Context, attrs: AttributeSet) : View(context, attrs) {

    private val paint = Paint().apply {
        style = Paint.Style.FILL
        color = context.getColor(R.color.mainBlue)
    }

    private val path = Path()

    override fun onDraw(canvas: Canvas) {
        super.onDraw(canvas)
        val width = width
        val height = height

        val radius = width * 0.3f // Radio para la media luna que abarque toda la pantalla

        path.reset()
        path.moveTo(0f, 0f) // Mover al medio del borde izquierdo
        path.arcTo(
            0f, 0f - radius,
            width.toFloat(), 0f + radius,
            180f, -180f, false
        )
        path.lineTo(width.toFloat(), height.toFloat())
        path.lineTo(0f, height.toFloat())

        path.close()

        canvas.drawPath(path, paint)
    }
}
