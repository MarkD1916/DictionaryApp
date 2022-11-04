package com.example.dictionaryapp.data.local.dao

import androidx.room.*
import com.example.dictionaryapp.data.local.entity.GroupEntity
import com.example.dictionaryapp.data.local.entity.WordEntity
import com.example.vocabularytrainer.data.local.home.entity.relations.GroupWithWords
import kotlinx.coroutines.flow.Flow

@Dao
interface DictionaryDao {

    //Group query
    @Query("SELECT * FROM GroupTable")
    fun selectAllGroup(): Flow<List<GroupEntity>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun addNewGroup(group: GroupEntity)

    @Query("SELECT * FROM GroupTable WHERE groupId= :id")
    fun getEntityById(id: String): Flow<GroupEntity>

    //Word query
    @Query("SELECT * FROM WordTable")
    fun selectAllWord(): Flow<List<WordEntity>>

    @Query("SELECT * FROM WordTable WHERE wordId = :id")
    fun selectWordById(id: String): Flow<WordEntity>


    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun addNewWord(wordEntity: WordEntity)

    @Query("DELETE FROM WordTable WHERE wordId = :wordId")
    suspend fun deleteWord(wordId: String)

    //Group and word query
    @Transaction
    @Query("SELECT * FROM GroupTable WHERE groupId = :groupId")
    fun selectGroupWithWords(groupId: String): Flow<List<GroupWithWords>>

    @Transaction
    @Query("DELETE FROM GroupTable WHERE groupId = :groupId")
    suspend fun deleteGroup(groupId: String)

}
