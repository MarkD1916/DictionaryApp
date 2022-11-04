package com.example.dictionaryapp.data.local.entity

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.core.data.db.BaseEntity

@Entity(tableName = "GroupTable")
data class GroupEntity(
    @PrimaryKey
    val groupId: String = "",
    val name: String = ""
): BaseEntity