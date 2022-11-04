package com.example.dictionaryapp.util

import org.junit.Assert.assertEquals
import org.junit.Test

/**
 * Methods for processing word when user trains it
 */
class WordUtilsTest {
    companion object {
        const val TEST_SENTENCE = "Hello, my dear friends! I like to meet you"
        val TEST_SENTENCE_WORDS =
            listOf("Hello", "my", "dear", "friends", "I", "like", "to", "meet", "you")


        const val TEST_WRONG_SENTENCE = "Hello, my der friends! I liking too met you"
        const val TEST_WORD = "Kotlin"
        const val TEST_WRONG_WORD = "Kutlin"
        const val TEST_WRONG_WORD_BIGGER = "Kqtlinsung"
        const val TEST_WRONG_WORD_SMALLER = "Kqtli"
    }


    @Test
    fun `find_all_word_in_sentence`() {
        val allWords = WordUtils.findAllWords(TEST_SENTENCE)
        assertEquals(allWords, TEST_SENTENCE_WORDS)
    }

    @Test
    fun `get_words_difference_same_len`() {
        val correctWords = TEST_WORD

        val wrongWords = TEST_WRONG_WORD

        val difference = WordUtils.getWordsDifference(wrongWords,correctWords)

        assertEquals(difference, listOf(1,6))
    }

    @Test
    fun `get_words_difference_user_len_bigger_than_correct`() {
        val correctWords = TEST_WORD

        val wrongWords = TEST_WRONG_WORD_BIGGER

        val difference = WordUtils.getWordsDifference(wrongWords,correctWords)

        assertEquals(difference, listOf(1,6,7,8,9))
    }

    @Test
    fun `get_words_difference_user_len_smaller_than_correct`() {
        val correctWords = TEST_WORD

        val wrongWords = TEST_WRONG_WORD_SMALLER

        val difference = WordUtils.getWordsDifference(wrongWords,correctWords)

        assertEquals(difference, listOf(1,5))
    }

}