package com.example.dictionaryapp.data.local.dao

import androidx.room.Dao
import androidx.room.Query
import kotlinx.coroutines.flow.Flow

@Dao
interface TrainDao {

    @Query("SELECT nativeWord FROM WordTable WHERE groupId = :groupId")
    fun getAllNativeWord(groupId: String): Flow<List<String>>

    @Query("SELECT translate FROM WordTable WHERE groupId = :groupId")
    fun getAllTranslateWord(groupId: String): Flow<List<String>>

    @Query("SELECT nativeWord FROM WordTable ORDER BY RANDOM() LIMIT :number")
    fun getNativeWordByCount(groupId: String, number: Int): Flow<List<String>>

    @Query("SELECT translate FROM WordTable ORDER BY RANDOM() LIMIT :number")
    fun getTranslateWordByCount(groupId: String, number: Int): Flow<List<String>>
}