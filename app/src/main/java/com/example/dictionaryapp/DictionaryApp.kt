package com.example.dictionaryapp

import android.app.Application
import com.example.dictionaryapp.di.AppComponent
import com.example.dictionaryapp.di.AppModule
import com.example.dictionaryapp.di.DaggerAppComponent
import com.example.spacextestapp.di.*

class DictionaryApp: Application() {

    lateinit var appComponent: AppComponent

    override fun onCreate() {
        super.onCreate()
        appComponent = DaggerAppComponent.builder()
            .appModule(AppModule(this))
            .build()
    }

}