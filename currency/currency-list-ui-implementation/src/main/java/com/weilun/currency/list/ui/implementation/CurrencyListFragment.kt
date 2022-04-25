package com.weilun.currency.list.ui.implementation

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope
import com.weilun.core.utils.dispatchers.DispatchersProvider
import com.weilun.core.utils.imageloder.ImageLoader
import com.weilun.core.utils.resources.ResourcesProvider
import com.weilun.currency.list.ui.implementation.adapter.CurrencyListAdapter
import com.weilun.currency.list.ui.implementation.factory.CurrencyIconFactory
import com.weilun.currency.list.ui.implementation.factory.ViewHolderFactory
import com.weilun.currency.list.ui.implementation.viewbinder.CurrencyListViewBinder
import com.weilun.currency.list.ui.implementation.viewmodel.CurrencyListViewModel
import kotlinx.coroutines.flow.collectLatest
import org.koin.android.ext.android.inject
import org.koin.androidx.viewmodel.ext.android.viewModel

class CurrencyListFragment : Fragment() {

    companion object {
        fun getInstance(): Fragment {
            return CurrencyListFragment()
        }
    }

    private val resourcesProvider: ResourcesProvider by inject()
    private val imageLoader: ImageLoader by inject()
    private val adapter: CurrencyListAdapter by lazy {
        CurrencyListAdapter(
            ViewHolderFactory(
                LayoutInflater.from(requireContext()),
                CurrencyIconFactory(lazy { resourcesProvider }),
                lazy { imageLoader }
            )
        )
    }
    private val viewBinder: CurrencyListViewBinder by lazy {
        CurrencyListViewBinder(requireContext(), requireView(), adapter).also { it.initView() }
    }
    private val viewModel: CurrencyListViewModel by viewModel()

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.currencies_list_fragment, container, false)
    }

    override fun onResume() {
        super.onResume()
        lifecycleScope.launchWhenResumed {
            viewBinder.whenViewActionEmitted().collectLatest {
                viewModel.handleViewAction(it)
            }
        }
        lifecycleScope.launchWhenResumed {
            viewModel.whenCurrencyDataChanged().collectLatest {
                viewBinder.bindViewState(it)
            }
        }
        viewModel.onResume()
    }

    override fun onPause() {
        super.onPause()
        viewModel.onPause()
    }
}