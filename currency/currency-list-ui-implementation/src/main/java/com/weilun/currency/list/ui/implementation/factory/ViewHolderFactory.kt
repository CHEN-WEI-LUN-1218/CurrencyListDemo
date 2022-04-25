package com.weilun.currency.list.ui.implementation.factory

import android.view.LayoutInflater
import android.view.ViewGroup
import com.weilun.core.utils.imageloder.ImageLoader
import com.weilun.currency.list.ui.implementation.R
import com.weilun.currency.list.ui.implementation.viewholder.CurrencyItemViewHolder

//Create ViewHolderFactory for later scaling
class ViewHolderFactory(
    private val layoutInflater: LayoutInflater,
    private val currencyIconFactory: CurrencyIconFactory,
    private val imageLoader: Lazy<ImageLoader>
) {

    fun create(parent: ViewGroup): CurrencyItemViewHolder {
        val view = layoutInflater.inflate(R.layout.currency_item, parent, false)
        return CurrencyItemViewHolder(view, currencyIconFactory, imageLoader)
    }
}