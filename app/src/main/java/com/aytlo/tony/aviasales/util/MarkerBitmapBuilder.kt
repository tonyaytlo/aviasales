package com.aytlo.tony.aviasales.util

import android.annotation.SuppressLint
import android.content.Context
import android.graphics.*
import android.view.LayoutInflater
import android.view.View
import android.widget.TextView
import com.aytlo.tony.aviasales.R
import com.aytlo.tony.aviasales.extension.android.content.getColorRes

object MarkerBitmapBuilder {

    fun buildBitmap(context: Context, title: String): Bitmap {
        val measuredView = getMeasuredView(context, title)
        val conf = Bitmap.Config.ARGB_8888
        val bitmap = Bitmap.createBitmap(measuredView.measuredWidth, measuredView.measuredHeight, conf)
        val canvas = Canvas(bitmap)
        val paintText = Paint().apply {
            textSize = context.resources.getDimension(R.dimen.text_size_xnormal)
            color = Color.WHITE
            typeface = Typeface.create(Typeface.DEFAULT, Typeface.BOLD)
            textAlign = Paint.Align.CENTER
        }
        val cornerPath = CornerPathEffect(context.resources.getDimension(R.dimen.marker_corner_radius))
        val backgroundPaint = Paint().apply {
            isAntiAlias = true
            color = context.getColorRes(R.color.colorMarker)
            pathEffect = cornerPath
        }
        val backgroundBorderPaint = Paint().apply {
            color = Color.WHITE
            style = Paint.Style.STROKE
            strokeWidth = context.resources.getDimension(R.dimen.marker_border)
            pathEffect = cornerPath
        }
        val markerRect = RectF(0F, 0F, canvas.width.toFloat(), canvas.height.toFloat())
        val borderInset = backgroundBorderPaint.strokeWidth / 2

        canvas.run {
            drawRect(markerRect, backgroundPaint)
            drawRect(markerRect.apply { inset(borderInset, borderInset) }, backgroundBorderPaint)
            translate(0f, -(paintText.descent() + paintText.ascent()) / 2)
            drawText(title, measuredView.measuredWidth / 2f, measuredView.measuredHeight / 2F, paintText)
        }

        return bitmap
    }

    @SuppressLint("InflateParams")
    private fun getMeasuredView(context: Context, title: String): View {
        val inflater = context.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater
        val viewMarker = inflater.inflate(R.layout.w_marker, null)
        val tvTitle = viewMarker.findViewById<TextView>(R.id.tvTitle)

        tvTitle.text = title
        viewMarker.measure(View.MeasureSpec.UNSPECIFIED, View.MeasureSpec.UNSPECIFIED)
        viewMarker.layout(0, 0, viewMarker.measuredWidth, viewMarker.measuredHeight)
        return tvTitle
    }
}