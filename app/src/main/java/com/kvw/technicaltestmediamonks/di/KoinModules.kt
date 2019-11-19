package com.kvw.technicaltestmediamonks.di

import com.kvw.technicaltestmediamonks.models.User
import com.kvw.technicaltestmediamonks.services.AlbumService
import com.kvw.technicaltestmediamonks.services.UserService
import com.kvw.technicaltestmediamonks.ui.user_albums.UserAlbumsViewModel
import com.kvw.technicaltestmediamonks.ui.users.UsersViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object KoinModules {

    val retrofitModule = module {
        single { provideRetrofit() }
        factory { provideUserService(get()) }
        factory { provideAlbumService(get()) }
    }

    val viewModelModule = module {
        viewModel { UsersViewModel(get()) }
        viewModel { (user: User) ->UserAlbumsViewModel(get(), user) }
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

    fun provideAlbumService(retrofit: Retrofit) =
        retrofit.create(AlbumService::class.java)

}