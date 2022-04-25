package com.weilun.core.utils.log

import android.util.Log
import android.util.Log.*
import timber.log.Timber

class DebugTree : Timber.Tree() {
    companion object {
        const val DEFAULT_TAG = "DemoApp"
    }

    override fun log(priority: Int, tag: String?, message: String, t: Throwable?) {
        when (priority) {
            VERBOSE -> Log.v(tag ?: DEFAULT_TAG, message)
            DEBUG -> Log.d(tag ?: DEFAULT_TAG, message)
            INFO -> Log.i(tag ?: DEFAULT_TAG, message)
            WARN -> Log.w(tag ?: DEFAULT_TAG, message)
            ERROR -> Log.e(tag ?: DEFAULT_TAG, message + getStackTraceString(t))
        }
    }
}