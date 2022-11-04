package com.example.dictionaryapp.data.local.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.PrimaryKey
import com.example.core.data.db.BaseEntity

@Entity(
    tableName = "WordTable",
    foreignKeys = [
        ForeignKey(
            entity = GroupEntity::class,
            parentColumns = ["groupId"],
            childColumns = ["groupId"],
            onDelete = ForeignKey.CASCADE //<<<<<
        )
    ])
data class WordEntity(
    @PrimaryKey(autoGenerate = false) val wordId: String,
    @ColumnInfo(name = "groupId") val groupId: String,
    val nativeWord: String,
    val translate: String,
    val transcription: String?
) : BaseEntity