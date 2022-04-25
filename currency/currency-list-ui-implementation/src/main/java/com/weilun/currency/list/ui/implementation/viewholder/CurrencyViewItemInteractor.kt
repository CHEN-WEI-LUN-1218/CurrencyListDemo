package com.weilun.currency.list.ui.implementation.viewholder

import com.weilun.currency.list.ui.implementation.model.CurrencyListViewAction
import kotlinx.coroutines.channels.BufferOverflow
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.SharedFlow
import kotlinx.coroutines.flow.asSharedFlow

class CurrencyViewItemInteractor {
    private val listViewActionFlow: MutableSharedFlow<CurrencyListViewAction> by lazy {
        MutableSharedFlow(extraBufferCapacity = 1,onBufferOverflow = BufferOverflow.DROP_OLDEST)
    }

    fun intercept(action: CurrencyListViewAction) {
        listViewActionFlow.tryEmit(action)
    }

    fun whenItemViewIntercept(): SharedFlow<CurrencyListViewAction> {
        return listViewActionFlow.asSharedFlow()
    }
}