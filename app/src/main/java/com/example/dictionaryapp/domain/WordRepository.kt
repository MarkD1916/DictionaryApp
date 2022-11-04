package com.example.dictionaryapp.domain

import com.example.core.data.db.BaseLocalRepository
import com.example.dictionaryapp.data.local.entity.WordEntity
import kotlinx.coroutines.flow.StateFlow

interface WordRepository: BaseLocalRepository<WordEntity> {

    val words: StateFlow<List<WordEntity>>

    fun getWordsByGroupId(groupId: String)
}