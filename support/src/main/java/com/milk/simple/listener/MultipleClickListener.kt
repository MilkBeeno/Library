package com.milk.simple.listener

import android.view.View

/**
 * @param ignoreDifferView 1.true 表示连续点击两个不同的 View 点击响应事件受到时间的限制 , 点击防抖对不同的 View 都有效
 *                         2.false 表示连续点击两个不同的 View 分别响应不同 View 的点击事件 , 点击防抖只对相同的 View 有效
 */
abstract class MultipleClickListener(
    private val ignoreDifferView: Boolean = false,
    private val minInterval: Long = 500L
) : View.OnClickListener {
    private var lastClickView: View? = null
    private var lastClickTime: Long = 0
    private val currentTime: Long
        get() = System.currentTimeMillis()

    override fun onClick(view: View?) {
        if (view == null) return
        if (ignoreDifferView) {
            if (currentTime - lastClickTime > minInterval) {
                onMultipleClick(view)
                lastClickTime = currentTime
            }
        } else {
            if (currentTime - lastClickTime > minInterval || lastClickView != view) {
                onMultipleClick(view)
                lastClickTime = currentTime
                lastClickView = view
            }
        }
    }

    abstract fun onMultipleClick(view: View)
}