package com.milk.simple.ktx

import android.app.Activity
import android.os.Build
import androidx.annotation.ColorRes
import androidx.annotation.StringRes

fun Activity.color(@ColorRes resId: Int): Int {
    return if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M)
        resources.getColor(resId, theme)
    else
        resources.getColor(resId)
}

fun Activity.string(@StringRes resId: Int): String {
    return resources.getString(resId)
}