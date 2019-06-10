package com.aytlo.tony.aviasales.extension.android.content

import android.content.Context
import android.widget.Toast
import android.widget.Toast.LENGTH_SHORT
import androidx.core.content.ContextCompat

fun Context.getColorRes(resId: Int) = ContextCompat.getColor(this, resId)

fun Context.showToast(text: String?, duration: Int = LENGTH_SHORT) {
    Toast.makeText(this, text ?: "", duration).show()
}