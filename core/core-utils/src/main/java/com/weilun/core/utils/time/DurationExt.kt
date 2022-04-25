package com.weilun.core.utils.time

import kotlin.time.Duration
import kotlin.time.DurationUnit
import kotlin.time.ExperimentalTime

@OptIn(ExperimentalTime::class)
fun Duration.toTimeInMillis(): Double {
    return this.toDouble(DurationUnit.MILLISECONDS)
}