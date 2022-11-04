package com.example.dictionaryapp.data.local


import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.dictionaryapp.data.local.dao.DictionaryDao
import com.example.dictionaryapp.data.local.entity.GroupEntity
import com.example.dictionaryapp.data.local.entity.WordEntity

@Database(
entities = [GroupEntity::class, WordEntity::class],
version = 1
)
abstract class DictionaryDatabase: RoomDatabase() {
    abstract val dictionaryDao: DictionaryDao
}