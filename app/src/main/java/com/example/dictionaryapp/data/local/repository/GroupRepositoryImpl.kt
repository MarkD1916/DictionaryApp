package com.example.dictionaryapp.data.local.repository

import com.example.dictionaryapp.data.local.dao.DictionaryDao
import com.example.dictionaryapp.data.local.entity.GroupEntity
import com.example.dictionaryapp.domain.GroupRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GroupRepositoryImpl @Inject constructor(private val dao: DictionaryDao): GroupRepository {

    override fun selectAllEntity(): Flow<List<GroupEntity>> {
        return dao.selectAllGroup()
    }

    override suspend fun addEntity(entity: GroupEntity) {
        dao.addNewGroup(entity)
    }

    override suspend fun deleteEntity(id: String) {
        dao.deleteGroup(id)
    }

    override fun getEntityById(id: String): Flow<GroupEntity> {
        return dao.getEntityById(id)
    }

}