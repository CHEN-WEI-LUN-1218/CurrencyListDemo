package com.weilun.currency.list.ui.implementation.viewholder

import android.graphics.drawable.Drawable
import android.view.View
import androidx.appcompat.widget.AppCompatImageView
import androidx.appcompat.widget.AppCompatTextView
import com.weilun.core.utils.imageloder.ImageLoader
import com.weilun.currency.list.core.bridge.model.CurrencyData
import com.weilun.currency.list.ui.implementation.R
import com.weilun.currency.list.ui.implementation.factory.CurrencyIconFactory
import org.junit.Before
import org.junit.Test
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.MockitoAnnotations
import org.mockito.kotlin.mock
import org.mockito.kotlin.whenever

class CurrencyItemViewHolderTest {

    @Mock
    lateinit var view: View

    @Mock
    lateinit var currencyIconFactory: CurrencyIconFactory

    @Mock
    lateinit var imageLoader: ImageLoader

    @Mock
    lateinit var ivCurrencyIcon: AppCompatImageView

    @Mock
    lateinit var tvCurrencyName: AppCompatTextView

    @Mock
    lateinit var tvCurrencySymbol: AppCompatTextView

    @Mock
    lateinit var interactor: CurrencyViewItemInteractor

    private lateinit var holder: CurrencyItemViewHolder

    @Before
    fun setUp() {
        MockitoAnnotations.openMocks(this)
        holder = CurrencyItemViewHolder(view, currencyIconFactory, lazy { imageLoader }, lazy { interactor })
        whenever(view.findViewById<AppCompatImageView>(R.id.ivCurrencyIcon)).thenReturn(ivCurrencyIcon)
        whenever(view.findViewById<AppCompatTextView>(R.id.tvCurrencyName)).thenReturn(tvCurrencyName)
        whenever(view.findViewById<AppCompatTextView>(R.id.tvCurrencySymbol)).thenReturn(tvCurrencySymbol)
    }

    @Test
    fun bindView() {
        val mockDrawable = mock<Drawable>()
        whenever(currencyIconFactory.getIcon("symbol1")).thenReturn(mockDrawable)
        holder.bindView(CurrencyData("id1", "name1", "symbol1"))
        Mockito.verify(ivCurrencyIcon).setImageDrawable(mockDrawable)
        Mockito.verify(tvCurrencyName).text = "name1"
        Mockito.verify(tvCurrencySymbol).text = "symbol1"
    }
}