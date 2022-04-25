package com.weilun.currency.list.ui.implementation.viewholder

import android.view.View
import androidx.appcompat.widget.AppCompatImageView
import androidx.appcompat.widget.AppCompatTextView
import androidx.recyclerview.widget.RecyclerView
import com.weilun.core.utils.imageloder.ImageLoader
import com.weilun.currency.list.core.bridge.model.CurrencyData
import com.weilun.currency.list.ui.implementation.R
import com.weilun.currency.list.ui.implementation.factory.CurrencyIconFactory
import com.weilun.currency.list.ui.implementation.model.CurrencyListViewAction

class CurrencyItemViewHolder(
    private val view: View,
    private val currencyIconFactory: CurrencyIconFactory,
    private val imageLoader: Lazy<ImageLoader>,
    private val interactor: Lazy<CurrencyViewItemInteractor>
) : RecyclerView.ViewHolder(view) {

    internal val ivCurrencyIcon: AppCompatImageView by lazy { view.findViewById(R.id.ivCurrencyIcon) }
    internal val tvCurrencyName: AppCompatTextView by lazy { view.findViewById(R.id.tvCurrencyName) }
    internal val tvCurrencySymbol: AppCompatTextView by lazy { view.findViewById(R.id.tvCurrencySymbol) }

    fun bindView(currencyData: CurrencyData) {
        itemView.setOnClickListener {
            interactor.value.intercept(CurrencyListViewAction.SelectItem(currencyData))
        }
        val drawable = currencyIconFactory.getIcon(currencyData.symbol)
        ivCurrencyIcon.setImageDrawable(drawable)
        imageLoader.value.loadImage("https://1234", drawable, ivCurrencyIcon)
        tvCurrencyName.text = currencyData.name
        tvCurrencySymbol.text = currencyData.symbol
    }
}