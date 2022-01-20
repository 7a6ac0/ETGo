package tabacowang.me.etgo.di

import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module
import tabacowang.me.etgo.ui.main.MainViewModel

val viewModelModule = module {
    viewModel { MainViewModel(get()) }
}