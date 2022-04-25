package com.weilun.currency.list.ui.implementation.factory

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.appcompat.widget.AppCompatImageView
import androidx.appcompat.widget.AppCompatTextView
import com.weilun.core.utils.imageloder.ImageLoader
import com.weilun.currency.list.ui.implementation.R
import com.weilun.currency.list.ui.implementation.viewholder.CurrencyViewItemInteractor
import org.junit.Assert
import org.junit.Before
import org.junit.Test
import org.mockito.Mock
import org.mockito.MockitoAnnotations
import org.mockito.kotlin.whenever

class ViewHolderFactoryTest {


    @Mock
    lateinit var layoutInflater: LayoutInflater

    @Mock
    lateinit var currencyIconFactory: CurrencyIconFactory

    @Mock
    lateinit var imageLoader: ImageLoader

    @Mock
    lateinit var parentView: ViewGroup

    @Mock
    lateinit var mockIvCurrencyIcon: AppCompatImageView

    @Mock
    lateinit var mockTvName: AppCompatTextView

    @Mock
    lateinit var mockTvSymbol: AppCompatTextView

    @Mock
    lateinit var interactor: CurrencyViewItemInteractor

    private lateinit var factory: ViewHolderFactory

    @Before
    fun setUp() {
        MockitoAnnotations.openMocks(this)
        factory = ViewHolderFactory(layoutInflater, currencyIconFactory, lazy { imageLoader }, lazy { interactor })
    }

    @Test
    fun create() {
        whenever(parentView.findViewById<AppCompatImageView>(R.id.ivCurrencyIcon)).thenReturn(mockIvCurrencyIcon)
        whenever(parentView.findViewById<AppCompatTextView>(R.id.tvCurrencySymbol)).thenReturn(mockTvSymbol)
        whenever(parentView.findViewById<AppCompatTextView>(R.id.tvCurrencyName)).thenReturn(mockTvName)
        whenever(layoutInflater.inflate(R.layout.currency_item, parentView, false)).thenReturn(parentView)
        val actual = factory.create(parentView)
        Assert.assertEquals(mockIvCurrencyIcon, actual.ivCurrencyIcon)
        Assert.assertEquals(mockTvName, actual.tvCurrencyName)
        Assert.assertEquals(mockTvSymbol, actual.tvCurrencySymbol)
    }
}