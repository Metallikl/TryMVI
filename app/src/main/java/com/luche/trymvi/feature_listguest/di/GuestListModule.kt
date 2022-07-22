package com.luche.trymvi.feature_listguest.di

import com.luche.trymvi.feature_listguest.data.datasource.GuestListLocalDatabase
import com.luche.trymvi.feature_listguest.data.datasource.GuestListLocalDatabaseImpl
import com.luche.trymvi.feature_listguest.data.repository.GuestListRepositoryImpl
import com.luche.trymvi.feature_listguest.domain.repository.GuestListRepository
import com.luche.trymvi.feature_listguest.domain.usecase.GetGuestListUseCase
import com.luche.trymvi.feature_listguest.domain.usecase.GetGuestListUseCaseImpl
import com.luche.trymvi.feature_listguest.presentation.viewmodel.GuestListViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

object GuestListModule {
    val guestListModule = module(override = true) {
        viewModel {
            GuestListViewModel(getGuestListUseCase = get())
        }

        factory<GetGuestListUseCase> {
            GetGuestListUseCaseImpl(repository = get())
        }

        single<GuestListRepository> {
            GuestListRepositoryImpl(guestListLocalDatabase = get())
        }

        single<GuestListLocalDatabase> {
            GuestListLocalDatabaseImpl(guestDao = get())
        }
    }

}
