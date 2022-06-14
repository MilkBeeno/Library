package com.milk.simple.ktx

import android.app.Activity
import android.graphics.Color
import android.view.View
import android.widget.FrameLayout
import android.widget.LinearLayout
import androidx.annotation.ColorInt
import androidx.appcompat.widget.LinearLayoutCompat
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.core.view.WindowCompat
import androidx.core.view.WindowInsetsCompat
import androidx.core.view.WindowInsetsControllerCompat

fun Activity.setStatusBarVisible(isVisible: Boolean) {
    WindowCompat.setDecorFitsSystemWindows(window, isVisible)
    WindowInsetsControllerCompat(window, window.decorView).let { controller ->
        if (isVisible) {
            controller.show(WindowInsetsCompat.Type.statusBars())
        } else {
            controller.hide(WindowInsetsCompat.Type.statusBars())
        }
        controller.systemBarsBehavior =
            WindowInsetsControllerCompat.BEHAVIOR_SHOW_TRANSIENT_BARS_BY_SWIPE
    }
}

fun Activity.setStatusBarColor(@ColorInt color: Int) {
    window.statusBarColor = color
}

fun Activity.setStatusBarDark(dark: Boolean = true) {
    WindowInsetsControllerCompat(window, window.decorView)
        .isAppearanceLightStatusBars = dark
}

fun Activity.obtainStatusBarHeight(): Int {
    val resourceId: Int = resources
        .getIdentifier("status_bar_height", "dimen", "android")
    return if (resourceId > 0) resources.getDimensionPixelSize(resourceId) else 0
}

fun Activity.immersiveStatusBar(view: View, dark: Boolean = true) {
    setStatusBarDark(dark)
    setStatusBarVisible(true)
    setStatusBarColor(Color.TRANSPARENT)
    WindowCompat.setDecorFitsSystemWindows(window, false)
    view.statusBarPadding()
}

private fun View.statusBarPadding() {
    try {
        if (context !is Activity) return
        val activity = context as Activity
        layoutParams = when (parent) {
            is FrameLayout -> {
                val params = layoutParams as FrameLayout.LayoutParams
                params.topMargin = activity.obtainStatusBarHeight()
                params
            }
            is LinearLayout -> {
                val params = layoutParams as LinearLayout.LayoutParams
                params.topMargin = activity.obtainStatusBarHeight()
                params
            }
            is LinearLayoutCompat -> {
                val params = layoutParams as LinearLayoutCompat.LayoutParams
                params.topMargin = activity.obtainStatusBarHeight()
                params
            }
            is ConstraintLayout -> {
                val params = layoutParams as ConstraintLayout.LayoutParams
                params.topMargin = activity.obtainStatusBarHeight()
                params
            }
            else -> layoutParams
        }
    } catch (e: Exception) {
        e.printStackTrace()
    }
}
