package com.milk.simple.mdr

import android.content.Context
import android.os.Parcelable
import com.tencent.mmkv.MMKV

object KvManger {
    private val kv by lazy { MMKV.defaultMMKV() }

    fun initialize(context: Context) {
        MMKV.initialize(context)
    }

    fun put(key: String, value: Any?) {
        when (value) {
            is Boolean -> kv.encode(key, value)
            is Int -> kv.encode(key, value)
            is Long -> kv.encode(key, value)
            is Float -> kv.encode(key, value)
            is Double -> kv.encode(key, value)
            is String -> kv.encode(key, value)
            is ByteArray -> kv.encode(key, value)
            is Parcelable -> kv.encode(key, value)
            is Set<*>? -> try {
                kv.encode(key, value as Set<String>)
            } catch (e: Exception) {
                e.printStackTrace()
            }
            else -> return
        }
    }

    fun getBoolean(key: String, default: Boolean = false): Boolean {
        return kv.decodeBool(key, default)
    }

    fun getString(key: String, default: String = ""): String {
        return kv.decodeString(key, default) ?: default
    }
}