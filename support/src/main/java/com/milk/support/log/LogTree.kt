package com.milk.support.log

interface LogTree {
    fun log(priority: Int, tag: String?, message: String, t: Throwable?)
}