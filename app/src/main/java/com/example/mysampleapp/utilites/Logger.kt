package com.example.mysampleapp.utilites

import android.util.Log

object Logger {
    private const val TAG = "AppLogger"
    private var isDebugMode = true

    fun v(tag: String = TAG, message: String) {
        if (isDebugMode) Log.v(tag, message)
    }

    fun d(tag: String = TAG, message: String) {
        if (isDebugMode) Log.d(tag, message)
    }

    fun i(tag: String = TAG, message: String) {
        Log.i(tag, message)
    }

    fun w(tag: String = TAG, message: String) {
        Log.w(tag, message)
    }

    fun e(tag: String = TAG, message: String, throwable: Throwable? = null) {
        Log.e(tag, message, throwable)
    }
}