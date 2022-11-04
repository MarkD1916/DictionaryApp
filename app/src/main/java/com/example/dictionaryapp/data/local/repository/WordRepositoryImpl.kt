package com.example.dictionaryapp.data.local.repository

import com.example.dictionaryapp.data.local.dao.DictionaryDao
import com.example.dictionaryapp.data.local.entity.WordEntity
import com.example.dictionaryapp.domain.WordRepository
import com.example.vocabularytrainer.data.local.home.entity.relations.GroupWithWords
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch
import javax.inject.Inject

class WordRepositoryImpl @Inject constructor(private val dao: DictionaryDao) : WordRepository {

    private val _words: MutableStateFlow<List<WordEntity>> = MutableStateFlow(listOf<WordEntity>())
    override val words = _words.asStateFlow()

    override fun selectAllEntity(): Flow<List<WordEntity>> {
        return dao.selectAllWord()
    }

    override fun getEntityById(id: String): Flow<WordEntity> {
        return dao.selectWordById(id)
    }

    override fun getWordsByGroupId(groupId: String) {
        val wordsWithWords = dao.selectGroupWithWords(groupId)
        CoroutineScope(Dispatchers.IO).launch {
            wordsWithWords
                .collect { groupWithWords ->
                    _words.update {
                        groupWithWords[0].words
                    }
                }
        }
    }

    override suspend fun addEntity(entity: WordEntity) {
        dao.addNewWord(entity)
    }

    override suspend fun deleteEntity(id: String) {
        dao.deleteWord(id)
    }


}