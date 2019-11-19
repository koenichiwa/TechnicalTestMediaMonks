package com.kvw.technicaltestmediamonks.di

import com.kvw.technicaltestmediamonks.services.UserService
import com.kvw.technicaltestmediamonks.ui.users.UsersViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object KoinModules {

    val retrofitModule = module {
        single { provideRetrofit() }
        factory { provideUserService(get()) }
    }

    val viewModelModule = module {
        viewModel { UsersViewModel(get()) }
    }

    val JSON_PLACEHOLDER_BASE_URL = "https://jsonplaceholder.typicode.com/"

    fun provideRetrofit(): Retrofit {
        return Retrofit
            .Builder()
            .addConverterFactory(GsonConverterFactory.create())
            .baseUrl(JSON_PLACEHOLDER_BASE_URL)
            .build()
    }

    fun provideUserService(retrofit: Retrofit): UserService =
        retrofit.create(UserService::class.java)

}