package com.shevart.androidpracticeproject.util

import android.util.Log
import com.shevart.androidpracticeproject.BuildConfig

object RLogger {
    fun log(tag: String, msg: String) {
        if (BuildConfig.DEBUG) {
            Log.e(tag, msg)
        }
    }

    fun log(e: Throwable) {
        if (BuildConfig.DEBUG) {
            e.printStackTrace()
        }
    }
}