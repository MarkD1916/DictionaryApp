package com.example.dictionaryapp.util

import com.example.dictionaryapp.ext.containedIn
import com.example.dictionaryapp.ext.isNoEmptySubRange
import com.example.dictionaryapp.util.Constants.BIG_ERROR_EMPTY_RANGE_EXCEPTION
import com.example.dictionaryapp.util.Constants.BIG_ERROR_RANGE_EXCEPTION_MESSAGE
import com.example.dictionaryapp.util.Constants.SMALL_ERROR_EMPTY_RANGE_EXCEPTION
import com.example.dictionaryapp.util.Constants.SMALL_ERROR_RANGE_EXCEPTION_MESSAGE
import com.example.dictionaryapp.util.RegularExp.ONLY_PUNCTUATION_CHAR


enum class MistakeType {
    SMALL, BIG, NO
}

interface WordTrainProcessing {

    fun getWordsDifference(userWord: String, correctWord: String): List<Int>

    fun findAllWords(sentence: String): List<String>

    fun getMistakeType(wordLength: Int, mistakeNumber: Int): MistakeType

}

//ToDo немного неправильно работает проверка, но в целом потом можно доделать
object WordUtils : WordTrainProcessing {

    var errorSmallMargin = 1..20
        set(value) {
            if (value.containedIn(errorBigMargin)) {
                throw ErrorRangeException(SMALL_ERROR_RANGE_EXCEPTION_MESSAGE)
            }
            if (value.isNoEmptySubRange(errorBigMargin)) {
                throw ErrorRangeException(SMALL_ERROR_EMPTY_RANGE_EXCEPTION)
            }
            field = value
        }

    var errorBigMargin = 21..100
        set(value) {
            if (errorSmallMargin.containedIn(value)) {
                throw ErrorRangeException(BIG_ERROR_RANGE_EXCEPTION_MESSAGE)
            }
            if (errorSmallMargin.isNoEmptySubRange(value)) {
                throw ErrorRangeException(BIG_ERROR_EMPTY_RANGE_EXCEPTION)
            }
            field = value
        }

    private var errorRelation = 0

    override fun getWordsDifference(userWord: String, correctWord: String): List<Int> {
        val listOfErrorWordIndex = mutableListOf<Int>()
        for (item in (userWord zip correctWord)) {
            if (item.first != item.second) {
                listOfErrorWordIndex.add(userWord.indexOf(item.first))
            }
        }

        if (userWord.length != correctWord.length) {
            for (item in (userWord.length until correctWord.length)) {
                listOfErrorWordIndex.add(item)
            }

            for (item in (correctWord.length until userWord.length)) {
                listOfErrorWordIndex.add(item)
            }
        }

        return listOfErrorWordIndex
    }

    override fun findAllWords(sentence: String): List<String> {
        val separateWords = sentence.split(
            " ",
            ignoreCase = true
        )
        val separateWordsNoPunctuation = separateWords.map {
            it.replace(ONLY_PUNCTUATION_CHAR.toRegex(), "")
        }
        return separateWordsNoPunctuation
    }

    override fun getMistakeType(wordLength: Int, mistakeNumber: Int): MistakeType {

        if (mistakeNumber != 0) {
            errorRelation = (100 * (mistakeNumber.toFloat() / wordLength.toFloat())).toInt()
        }

        return when (errorRelation) {
            in errorSmallMargin -> MistakeType.SMALL
            in errorBigMargin -> MistakeType.BIG
            else -> MistakeType.NO
        }
    }

}






















