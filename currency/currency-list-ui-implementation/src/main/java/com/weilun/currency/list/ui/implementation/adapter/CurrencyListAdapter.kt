package com.weilun.currency.list.ui.implementation.adapter

import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import com.weilun.currency.list.core.bridge.model.CurrencyData
import com.weilun.currency.list.ui.implementation.factory.ViewHolderFactory
import com.weilun.currency.list.ui.implementation.model.CurrencyListViewAction
import com.weilun.currency.list.ui.implementation.viewholder.CurrencyItemViewHolder
import kotlinx.coroutines.flow.MutableSharedFlow

class CurrencyListAdapter(
    private val viewHolderFactory: ViewHolderFactory
) : ListAdapter<CurrencyData, CurrencyItemViewHolder>(DIFF) {

    companion object {
        val DIFF = object : DiffUtil.ItemCallback<CurrencyData>() {
            override fun areItemsTheSame(oldItem: CurrencyData, newItem: CurrencyData): Boolean {
                return oldItem.id == newItem.id
            }

            override fun areContentsTheSame(oldItem: CurrencyData, newItem: CurrencyData): Boolean {
                return oldItem.id == newItem.id
                    && oldItem.name == newItem.name
                    && oldItem.symbol == newItem.symbol
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CurrencyItemViewHolder {
        return viewHolderFactory.create(parent)
    }

    override fun onBindViewHolder(holder: CurrencyItemViewHolder, position: Int) {
        val item = getItem(position)
        holder.bindView(item)
    }
}