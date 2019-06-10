package com.aytlo.tony.aviasales.extension.android.content

import android.content.Context
import androidx.core.content.ContextCompat

fun Context.getColorRes(resId: Int) = ContextCompat.getColor(this, resId)