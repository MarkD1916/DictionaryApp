package com.example.core.data.sync

import com.example.core.data.application_catch.BaseCatch
import com.example.core.data.db.BaseEntity
import com.example.core.data.preference.BasePreferences
import com.example.core.data.remote.BaseResponse

interface BaseSyncInteractor<T : BaseEntity, P : BaseResponse> {

    suspend fun fetch()

    suspend fun push()

    suspend fun merge()

}


sealed class SyncType<T : BaseEntity?, P : BaseResponse?>(
    priority: Int,
    localData: T,
    remoteData: P
) {

    data class SyncFromDB(val priority: Int, val localData: BaseEntity?) :
        SyncType<BaseEntity?, BaseResponse?>(priority, localData, null)

    data class SyncFromCatch(val priority: Int, val localData: BaseEntity?) :
        SyncType<BaseCatch?, BaseResponse?>(priority, localData as BaseCatch, null)

    data class SyncFromPreference(val priority: Int, val localData: BaseEntity?) :
        SyncType<BasePreferences?, BaseResponse?>(priority, localData as BasePreferences, null)

    data class SyncFromServer(val priority: Int, val remoteData: BaseResponse?) :
        SyncType<BaseEntity?, BaseResponse?>(priority, null, remoteData)

}