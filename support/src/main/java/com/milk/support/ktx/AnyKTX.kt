package com.milk.support.ktx

fun Any?.safeToLong(): Long {
    if (this == null) return 0L
    if (this is Long) return this
    return try {
        when (this) {
            is Int -> this.toLong()
            is Short -> this.toLong()
            is Float -> this.toLong()
            is Double -> this.toLong()
            else -> 0L
        }
    } catch (e: Exception) {
        e.printStackTrace()
        0L
    }
}

fun Any?.safeToInt(): Int {
    if (this == null) return 0
    if (this is Int) return this
    return try {
        when (this) {
            is Short -> this.toInt()
            is Float -> this.toInt()
            is Long -> this.toInt()
            is Double -> this.toInt()
            else -> 0
        }
    } catch (e: Exception) {
        e.printStackTrace()
        0
    }
}

fun Any?.safeToFloat(): Float {
    if (this == null) return 0f
    if (this is Float) return this
    return try {
        when (this) {
            is Int -> this.toFloat()
            is Short -> this.toFloat()
            is Long -> this.toFloat()
            is Double -> this.toFloat()
            else -> 0f
        }
    } catch (e: Exception) {
        e.printStackTrace()
        0f
    }
}

fun Any?.safeToDouble(): Double {
    if (this == null) return 0.0
    if (this is Double) return this
    return try {
        when (this) {
            is Int -> this.toDouble()
            is Short -> this.toDouble()
            is Long -> this.toDouble()
            is Double -> this.toDouble()
            else -> 0.0
        }
    } catch (e: Exception) {
        e.printStackTrace()
        0.0
    }
}