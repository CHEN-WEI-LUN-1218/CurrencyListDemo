package com.weilun.core.utils.resources

import android.content.Context

class ResourcesProvider(
    private val context: Context
) {
    fun getDimensionPixelSize(resId: Int): Int {
        return context.resources.getDimensionPixelSize(resId)
    }
}