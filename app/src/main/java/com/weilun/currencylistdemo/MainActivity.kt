package com.weilun.currencylistdemo

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import com.weilun.currency.list.ui.bridge.CurrencyListViewProvider
import org.koin.android.ext.android.inject

class MainActivity : AppCompatActivity() {

    private val currencyListViewProvider: CurrencyListViewProvider<Fragment> by inject()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }

    override fun onResume() {
        super.onResume()
        val fragment = currencyListViewProvider.provideCurrencyListView()
        supportFragmentManager.beginTransaction().replace(R.id.flContainer, fragment).commit()
    }
}