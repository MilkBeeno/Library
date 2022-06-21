package com.milk.simple.keyboard

import android.content.Context
import android.content.SharedPreferences

object KeyBoardSize {
    private const val FILE_NAME = "keyboard.common"
    private const val KEY_KEYBOARD_HEIGHT = "sp.key.keyboard.height"

    @Volatile
    private var sp: SharedPreferences? = null

    fun save(context: Context, keyboardHeight: Int) {
        with(context)!!.edit()
            .putInt(KEY_KEYBOARD_HEIGHT, keyboardHeight)
            .apply()
    }

    operator fun get(context: Context, defaultHeight: Int): Int {
        return with(context)!!.getInt(KEY_KEYBOARD_HEIGHT, defaultHeight)
    }

    private fun with(context: Context): SharedPreferences? {
        if (sp == null) {
            synchronized(KeyBoardSize::class.java) {
                if (sp == null) {
                    sp = context.getSharedPreferences(FILE_NAME, Context.MODE_PRIVATE)
                }
            }
        }
        return sp
    }
}