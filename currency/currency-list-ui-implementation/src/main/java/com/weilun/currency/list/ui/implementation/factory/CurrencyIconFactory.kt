package com.weilun.currency.list.ui.implementation.factory

import android.graphics.Color
import android.graphics.drawable.Drawable
import com.weilun.components.icon.TextOvalDrawable
import com.weilun.core.utils.resources.ResourcesProvider
import com.weilun.currency.list.ui.implementation.R

class CurrencyIconFactory(
    private val resourcesProvider: Lazy<ResourcesProvider>
) {

    fun getIcon(text: String): Drawable {
        val size = resourcesProvider.value.getDimensionPixelSize(R.dimen.oval_icon_size)
        return TextOvalDrawable.Builder()
            .textColor(Color.WHITE)
            .width(size)
            .height(size)
            .build(text)
    }
}