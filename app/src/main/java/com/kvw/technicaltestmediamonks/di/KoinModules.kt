package com.kvw.technicaltestmediamonks.di

import android.content.Context
import android.net.Uri
import androidx.room.Room
import com.google.gson.GsonBuilder
import com.kvw.technicaltestmediamonks.business.models.AlbumModel
import com.kvw.technicaltestmediamonks.business.models.UserModel
import com.kvw.technicaltestmediamonks.business.repositories.AlbumRepository
import com.kvw.technicaltestmediamonks.business.repositories.AlbumRepositoryImpl
import com.kvw.technicaltestmediamonks.business.repositories.CommentRepository
import com.kvw.technicaltestmediamonks.business.repositories.CommentRepositoryImpl
import com.kvw.technicaltestmediamonks.business.repositories.PhotoRepository
import com.kvw.technicaltestmediamonks.business.repositories.PhotoRepositoryImpl
import com.kvw.technicaltestmediamonks.business.repositories.PostRepository
import com.kvw.technicaltestmediamonks.business.repositories.PostRepositoryImpl
import com.kvw.technicaltestmediamonks.business.repositories.UserRepository
import com.kvw.technicaltestmediamonks.business.repositories.UserRepositoryImpl
import com.kvw.technicaltestmediamonks.data.retrofit.services.AlbumService
import com.kvw.technicaltestmediamonks.data.retrofit.services.CommentService
import com.kvw.technicaltestmediamonks.data.retrofit.services.PhotoService
import com.kvw.technicaltestmediamonks.data.retrofit.services.PostService
import com.kvw.technicaltestmediamonks.data.retrofit.typeadapters.UriTypeAdapter
import com.kvw.technicaltestmediamonks.data.retrofit.services.UserService
import com.kvw.technicaltestmediamonks.data.room.AppDataBase
import com.kvw.technicaltestmediamonks.ui.albumphotos.AlbumPhotosViewModel
import com.kvw.technicaltestmediamonks.ui.useralbums.UserAlbumsViewModel
import com.kvw.technicaltestmediamonks.ui.userdetail.UserDetailViewModel
import com.kvw.technicaltestmediamonks.ui.userposts.UserPostsViewModel
import com.kvw.technicaltestmediamonks.ui.users.UsersViewModel
import org.koin.android.ext.koin.androidContext
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object KoinModules {
    private const val JSON_PLACEHOLDER_BASE_URL = "https://jsonplaceholder.typicode.com"
    private const val DATABASE_NAME = "jsonplaceholder_db"

    val retrofitModule = module {
        single { provideGsonConverterFactory() }
        single { provideRetrofit(get()) }
        factory { provideUserService(get()) }
        factory { provideAlbumService(get()) }
        factory { providePhotoService(get()) }
        factory { providePostService(get()) }
        factory { provideCommentService(get()) }
    }

    val viewModelModule = module {
        viewModel { UsersViewModel(get()) }
        viewModel { (user: UserModel) -> UserAlbumsViewModel(get(), user) }
        viewModel { (album: AlbumModel) -> AlbumPhotosViewModel(get(), album) }
        viewModel { (user: UserModel) -> UserPostsViewModel(get(), user) }
        viewModel { (user: UserModel) -> UserDetailViewModel(get(), get(), user) }
    }

    val repositoryModule = module {
        factory<AlbumRepository> { AlbumRepositoryImpl(get(), get(), get(), get()) }
        factory<UserRepository> { UserRepositoryImpl(get(), get()) }
        factory<PhotoRepository> { PhotoRepositoryImpl(get(), get()) }
        factory<PostRepository> { PostRepositoryImpl(get()) }
        factory<CommentRepository> { CommentRepositoryImpl(get()) }
    }

    val roomModule = module {
        single { provideRoomDatabase(androidContext()) }
        factory { provideAlbumDAO(get()) }
        factory { providePhotoDAO(get()) }
        factory { provideUserDAO(get()) }
    }

    private fun provideGsonConverterFactory(): GsonConverterFactory {
        GsonBuilder().registerTypeAdapter(Uri::class.java,
            UriTypeAdapter()
        )
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

    private fun provideCommentService(retrofit: Retrofit): CommentService =
        retrofit.create(CommentService::class.java)

    private fun providePostService(retrofit: Retrofit): PostService =
        retrofit.create(PostService::class.java)

    private fun provideRoomDatabase(context: Context) =
        Room.databaseBuilder(context, AppDataBase::class.java, DATABASE_NAME).build()

    private fun provideAlbumDAO(appDataBase: AppDataBase) = appDataBase.albumDAO()

    private fun provideUserDAO(appDataBase: AppDataBase) = appDataBase.userDAO()

    private fun providePhotoDAO(appDataBase: AppDataBase) = appDataBase.photoDAO()
}