package com.dm.testapp.di

import com.dm.testapp.repo.RepoDatasource
import com.dm.testapp.ui.detail.DetailViewModel
import com.dm.testapp.ui.list.ListViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val viewModelModule = module {

    viewModel { ListViewModel() }
    viewModel { DetailViewModel() }

}

val repoModule = module {
    single { RepoDatasource() }
}