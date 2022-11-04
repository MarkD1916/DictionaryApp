package com.example.dictionaryapp.di

import android.app.Application
import androidx.room.Room
import com.example.dictionaryapp.data.local.DictionaryDatabase
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class LocalDataModule {

    @Provides
    @Singleton
    fun provideDictionaryDatabase(app: Application): DictionaryDatabase {
        return Room.databaseBuilder(
            app,
            DictionaryDatabase::class.java,
            "dictionary_db"
        ).build()
    }


}