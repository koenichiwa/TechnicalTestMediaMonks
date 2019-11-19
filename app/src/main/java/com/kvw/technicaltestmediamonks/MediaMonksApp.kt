package com.kvw.technicaltestmediamonks

import android.app.Application
import com.kvw.technicaltestmediamonks.di.KoinModules
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin

class MediaMonksApp : Application(){
    override fun onCreate() {
        super.onCreate()
        startKoin {
            androidLogger()
            androidContext(this@MediaMonksApp)
            modules(listOf(KoinModules.retrofitModule, KoinModules.viewModelModule))
        }
    }
}