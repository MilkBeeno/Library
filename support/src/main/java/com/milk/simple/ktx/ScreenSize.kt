package com.milk.simple.ktx

import android.app.Activity
import android.content.Context
import android.content.res.Configuration
import android.graphics.Insets
import android.graphics.Rect
import android.os.Build
import android.util.DisplayMetrics
import android.view.WindowInsets

/**
 *   屏幕尺寸的大小获取
 * - [WindowInsetsCompat.Type.statusBars()]      状态栏
 * - [WindowInsetsCompat.Type.navigationBars()]  底部导航栏
 * - [WindowInsetsCompat.Type.captionBar()]      标题栏
 * - [WindowInsetsCompat.Type.systemBars()]      前三者全部
 */
fun Activity.obtainScreenWidth(): Int {
    return if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.R) {
        val windowMetrics = windowManager.currentWindowMetrics
        val bounds: Rect = windowMetrics.bounds
        val insets: Insets = windowMetrics.windowInsets.getInsetsIgnoringVisibility(
            WindowInsets.Type.systemBars()
        )
        if (resources.configuration.orientation == Configuration.ORIENTATION_LANDSCAPE
            && resources.configuration.smallestScreenWidthDp < 600
        ) {
            // landscape and phone
            val navigationBarSize: Int = insets.right + insets.left
            bounds.width() - navigationBarSize
        } else {
            // portrait or tablet
            bounds.width()
        }
    } else {
        val outMetrics = DisplayMetrics()
        windowManager.defaultDisplay.getMetrics(outMetrics)
        outMetrics.widthPixels
    }
}

fun Activity.obtainScreenHeight(): Int {
    return if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.R) {
        val windowMetrics = windowManager.currentWindowMetrics
        val bounds: Rect = windowMetrics.bounds
        val insets: Insets = windowMetrics.windowInsets.getInsetsIgnoringVisibility(
            WindowInsets.Type.systemBars()
        )
        if (resources.configuration.orientation == Configuration.ORIENTATION_LANDSCAPE
            && resources.configuration.smallestScreenWidthDp < 600
        ) {
            // landscape and phone
            bounds.height()
        } else {
            // portrait or tablet
            val navigationBarSize: Int = insets.bottom
            bounds.height() - navigationBarSize
        }
    } else {
        val outMetrics = DisplayMetrics()
        windowManager.defaultDisplay.getMetrics(outMetrics)
        outMetrics.heightPixels
    }
}

fun Context.obtainStatusBarHeight(): Int {
    val resourceId: Int = resources
        .getIdentifier("status_bar_height", "dimen", "android")
    return if (resourceId > 0) resources.getDimensionPixelSize(resourceId) else 0
}

fun Context.obtainNavigationBarHeight(): Int {
    val resourceId: Int = resources
        .getIdentifier("navigation_bar_height", "dimen", "android")
    return if (resourceId > 0) resources.getDimensionPixelSize(resourceId) else 0
}


