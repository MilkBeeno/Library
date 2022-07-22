package com.milk.simple.ktx

import android.content.Context
import android.util.TypedValue

fun Context.dp2px(value: Float): Float =
    TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, value, resources.displayMetrics)
