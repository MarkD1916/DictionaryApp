package com.example.dictionaryapp.di

import android.app.Application
import android.content.Context
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(modules = [AppModule::class, LocalDataModule::class])
interface AppComponent {
    fun context(): Context
    fun applicationContext(): Application
//    fun getApi(): LaunchesApi
}