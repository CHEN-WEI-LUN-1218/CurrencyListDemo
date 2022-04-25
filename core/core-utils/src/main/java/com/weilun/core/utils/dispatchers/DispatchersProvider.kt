package com.weilun.core.utils.dispatchers

import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.newFixedThreadPoolContext
import kotlinx.coroutines.newSingleThreadContext

class DispatchersProvider(
    private val _appInitDispatcher: CoroutineDispatcher? = null,
    private val _ioDispatcher: CoroutineDispatcher? = null,
    private val _mainDispatcher: CoroutineDispatcher? = null,
    private val _defaultDispatcher: CoroutineDispatcher? = null
) {

    val io by lazy(LazyThreadSafetyMode.NONE) { _ioDispatcher ?: Dispatchers.IO }
    val main by lazy(LazyThreadSafetyMode.NONE) { _mainDispatcher ?: Dispatchers.Main }
    val default by lazy(LazyThreadSafetyMode.NONE) { _defaultDispatcher ?: Dispatchers.Default }
    val appInitDispatcher by lazy(LazyThreadSafetyMode.NONE) {
        _appInitDispatcher ?: newFixedThreadPoolContext(8, "AppInitWorker")
    }
}
