package com.weilun.currencylistdemo

import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.FrameLayout
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import com.weilun.currency.list.ui.bridge.CurrencyListViewProvider
import org.koin.android.ext.android.inject

class MainActivity : AppCompatActivity() {

    private val currencyListViewProvider: CurrencyListViewProvider<Fragment> by inject()
    private val btLoad: Button by lazy { findViewById(R.id.btLoad) }
    private val flContainer: FrameLayout by lazy { findViewById(R.id.flContainer) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }

    override fun onStart() {
        super.onStart()
        btLoad.setOnClickListener {
            loadCurrencyView()
        }
    }

    private fun loadCurrencyView() {
        btLoad.visibility = View.GONE
        flContainer.visibility = View.VISIBLE
        val fragment = currencyListViewProvider.provideCurrencyListView()
        supportFragmentManager.beginTransaction().replace(R.id.flContainer, fragment).commit()
    }
}