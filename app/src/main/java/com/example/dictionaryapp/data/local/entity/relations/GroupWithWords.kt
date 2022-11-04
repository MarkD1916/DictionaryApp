package com.example.vocabularytrainer.data.local.home.entity.relations

import androidx.room.Embedded
import androidx.room.Relation
import com.example.dictionaryapp.data.local.entity.GroupEntity
import com.example.dictionaryapp.data.local.entity.WordEntity

data class GroupWithWords(
    @Embedded val group: GroupEntity,
    @Relation(
        parentColumn = "groupId",
        entityColumn = "groupId"
    )
    val words: List<WordEntity>
)
