package com.kvw.technicaltestmediamonks.di

import android.net.Uri
import com.google.gson.GsonBuilder
import com.kvw.technicaltestmediamonks.models.Album
import com.kvw.technicaltestmediamonks.models.User
import com.kvw.technicaltestmediamonks.services.AlbumService
import com.kvw.technicaltestmediamonks.services.PhotoService
import com.kvw.technicaltestmediamonks.services.UriTypeAdapter
import com.kvw.technicaltestmediamonks.services.UserService
import com.kvw.technicaltestmediamonks.ui.albumphotos.AlbumPhotosViewModel
import com.kvw.technicaltestmediamonks.ui.useralbums.UserAlbumsViewModel
import com.kvw.technicaltestmediamonks.ui.users.UsersViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object KoinModules {

    val retrofitModule = module {
        single { provideGsonConverterFactory() }
        single { provideRetrofit(get()) }
        factory { provideUserService(get()) }
        factory { provideAlbumService(get()) }
        factory { providePhotoService(get()) }
    }

    val viewModelModule = module {
        viewModel { UsersViewModel(get()) }
        viewModel { (user: User) -> UserAlbumsViewModel(get(), user) }
        viewModel { (album: Album) -> AlbumPhotosViewModel(get(), album) }
    }

    private const val JSON_PLACEHOLDER_BASE_URL = "https://jsonplaceholder.typicode.com/"

    private fun provideGsonConverterFactory(): GsonConverterFactory{
        GsonBuilder().registerTypeAdapter(Uri::class.java, UriTypeAdapter())
            .create()
            .let { return GsonConverterFactory.create(it) }
    }

    private fun provideRetrofit(converterFactory: GsonConverterFactory): Retrofit {

        return Retrofit
            .Builder()
            .addConverterFactory(converterFactory)
            .baseUrl(JSON_PLACEHOLDER_BASE_URL)
            .build()
    }

    private fun provideUserService(retrofit: Retrofit): UserService =
        retrofit.create(UserService::class.java)

    private fun provideAlbumService(retrofit: Retrofit): AlbumService =
        retrofit.create(AlbumService::class.java)

    private fun providePhotoService(retrofit: Retrofit): PhotoService =
        retrofit.create(PhotoService::class.java)

}