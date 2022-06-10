package com.milk.simple.log

import android.util.Log
import com.tencent.mmkv.BuildConfig
import timber.log.Timber

object Logger {

    fun initialize(
        tree: LogTree = object : LogTree {
            override fun log(priority: Int, tag: String?, message: String, t: Throwable?) = Unit
        }
    ) {
        Timber.plant(object : Timber.DebugTree() {
            override fun log(
                priority: Int, tag: String?, message: String, t: Throwable?
            ) {
                if (BuildConfig.DEBUG || Log.isLoggable("MyLog", Log.DEBUG)) {
                    super.log(priority, tag, message, t)
                    tree.log(priority, tag, message, t)
                }
            }
        })
    }

    fun d(message: String, tag: Any? = null) {
        setTag(tag)
        Timber.d(message)
    }

    fun e(message: String, tag: Any? = null) {
        setTag(tag)
        Timber.e(message)
    }

    fun v(message: String, tag: Any? = null) {
        setTag(tag)
        Timber.v(message)
    }

    fun i(message: String, tag: Any? = null) {
        setTag(tag)
        Timber.i(message)
    }

    private fun setTag(tag: Any? = null) {
        when (tag) {
            null -> return
            is String -> Timber.tag(tag)
            else -> Timber.tag(tag::class.java.name)
        }
    }
}