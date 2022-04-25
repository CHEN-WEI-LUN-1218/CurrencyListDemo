package com.weilun.core.utils.di

import com.weilun.core.utils.dispatchers.DispatchersProvider
import com.weilun.core.utils.imageloder.ImageLoader
import com.weilun.core.utils.resources.ResourcesProvider
import org.koin.dsl.module

object CoreUtilsModule {
    val module by lazy {
        module {
            factory { DispatchersProvider() }
            factory { ResourcesProvider(context = get()) }
            factory { ImageLoader(context = get()) }
        }
    }
}
