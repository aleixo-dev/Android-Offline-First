package com.nicolas.androidofflinefirst.di

import com.nicolas.androidofflinefirst.ui.theme.home.HomeViewModel
import org.koin.core.module.dsl.viewModel
import org.koin.dsl.module

val appModule = module {
    viewModel { HomeViewModel(get(), get()) }
}