package com.example.core.data.db

import kotlinx.coroutines.flow.Flow

interface BaseLocalRepository <T: BaseEntity> {

    fun selectAllEntity(): Flow<List<T>>

    suspend fun addEntity(entity: T)

    suspend fun deleteEntity(id: String)

    fun getEntityById(id: String): Flow<T>
}