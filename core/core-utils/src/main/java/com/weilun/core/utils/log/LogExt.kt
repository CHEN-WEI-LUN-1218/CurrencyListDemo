package com.weilun.core.utils.log

import timber.log.Timber

fun logErrorWithTag(tag: String, error: String, throwable: Throwable) {
    Timber.tag(tag)
    Timber.e(throwable, error)
}

fun Any.logError(throwable: Throwable) {
    Timber.tag(javaClass.name)
    Timber.e(throwable)
}

fun Any.logError(error: String, exception: Exception) {
    Timber.tag(javaClass.name)
    Timber.e(exception)
}

fun Any.logError(error: String, throwable: Throwable) {
    Timber.tag(javaClass.name)
    Timber.e(throwable)
}

fun Any.logError(error: String, vararg args: Any) {
    Timber.tag(javaClass.name)
    Timber.e(error, args)
}

fun logDebugWithTag(tag: String, message: String) {
    logDebug(tag, message)
}

fun Any.logDebug(message: String) {
    logDebug(tag = javaClass.name, message)
}

fun Any.logDebug(message: String, exception: Throwable) {
    logDebug(tag = javaClass.name, message)
}

private fun logDebug(tag: String, message: String) {
    Timber.tag(tag)
    Timber.d(message)
}

fun Any.logInfo(message: String, vararg args: Any) {
    Timber.tag(javaClass.name)
    Timber.i(message)
}

fun Any.logInfo(message: String) {
    Timber.tag(javaClass.name)
    Timber.i(message)
}