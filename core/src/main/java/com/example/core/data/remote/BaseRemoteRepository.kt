package com.example.core.data.remote

import com.example.core.data.Repository
import kotlinx.coroutines.flow.Flow

interface BaseRemoteRepository<T : BaseRequest, P : BaseResponse> : Repository {

    suspend fun postWithResponse(request: T): P

    suspend fun postNoResponse(request: T)

    suspend fun get(request: T): Flow<P>
}