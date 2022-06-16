package com.milk.simple.ktx

import android.graphics.Color
import android.text.NoCopySpan
import android.text.Spannable
import android.text.SpannableStringBuilder
import android.text.TextPaint
import android.text.method.LinkMovementMethod
import android.text.style.ClickableSpan
import android.text.style.ForegroundColorSpan
import android.view.View
import android.widget.TextView

fun TextView.setSpannableColor(vararg targets: Pair<String, Int>) {
    try {
        if (text.toString().trim().isBlank()) return
        val content = text.toString()
        val builder = SpannableStringBuilder(content)
        targets.forEach {
            if (content.contains(it.first)) {
                val colorSpan = ForegroundColorSpan(it.second)
                val startIndex = content.indexOf(it.first, ignoreCase = true)
                val endIndex = startIndex + it.first.length
                val colorFlags = Spannable.SPAN_EXCLUSIVE_EXCLUSIVE
                builder.setSpan(colorSpan, startIndex, endIndex, colorFlags)
            }
        }
        text = builder
    } catch (e: Exception) {
        e.printStackTrace()
    }
}

fun TextView.setSpannableClick(vararg targets: Pair<String, ClickableSpan>) {
    try {
        if (text.toString().trim().isBlank()) return
        val content = text.toString()
        val builder = SpannableStringBuilder(content)
        targets.forEach {
            if (content.contains(it.first)) {
                val startIndex = content.indexOf(it.first, ignoreCase = true)
                val endIndex = startIndex + it.first.length
                val colorFlags = Spannable.SPAN_INCLUSIVE_EXCLUSIVE
                builder.setSpan(it.second, startIndex, endIndex, colorFlags)
            }
        }
        movementMethod = LinkMovementMethod.getInstance()
        highlightColor = Color.TRANSPARENT
        text = builder
    } catch (e: Exception) {
        e.printStackTrace()
    }
}

abstract class NoCopyClickableSpan : ClickableSpan(), NoCopySpan {
    override fun onClick(p0: View) = Unit
}

fun colorClickableSpan(color: Int, clickRequest: () -> Unit) = object : NoCopyClickableSpan() {
    override fun updateDrawState(ds: TextPaint) {
        super.updateDrawState(ds)
        ds.color = color
        ds.isUnderlineText = false
        ds.clearShadowLayer()
    }

    override fun onClick(p0: View) = clickRequest()
}
