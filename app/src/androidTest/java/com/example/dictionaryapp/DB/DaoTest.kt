package com.vmakd1916gmail.com.ktornoteapp.DB


// for assertions on Java 8 types (Streams and java.util.Optional)
import androidx.room.Room
import androidx.test.core.app.ApplicationProvider
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.filters.SmallTest
import app.cash.turbine.test
import com.example.dictionaryapp.data.local.dao.DictionaryDao
import com.example.dictionaryapp.data.local.DictionaryDatabase
import com.example.dictionaryapp.data.local.entity.GroupEntity
import com.example.dictionaryapp.data.local.entity.WordEntity
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.*
import org.junit.After
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.rules.TestWatcher
import org.junit.runner.Description
import org.junit.runner.RunWith
import java.util.*

@ExperimentalCoroutinesApi
@RunWith(AndroidJUnit4::class)
@SmallTest
class DaoTest {

    companion object {
        const val TEST_GROUP_ID = "TEST_GROUP_ID"
        const val TEST_WORD_ID = "TEST_WORD_ID"

        const val TEST_GROUP_NAME = "TEST_GROUP_NAME"
        const val TEST_MAIN_GROUP_NAME = "TEST_MAIN_GROUP_NAME"
        const val TEST_WORD = "TEST_WORD"
        const val TEST_TRANSLATE = "TEST_TRANSLATE"
        const val TEST_TRANSCRIPTION = "TEST_TRANSCRIPTION"

    }

    @OptIn(ExperimentalCoroutinesApi::class)
    class TestDispatcherRule(
        private val testDispatcher: TestDispatcher = UnconfinedTestDispatcher()
    ) : TestWatcher() {
        override fun starting(description: Description) {
            Dispatchers.setMain(testDispatcher)
        }

        override fun finished(description: Description) {
            Dispatchers.resetMain()
        }
    }


    @get: Rule
    val dispatcherRule = TestDispatcherRule()


    private lateinit var database: DictionaryDatabase
    private lateinit var dictionaryDao: DictionaryDao

    /**
     * Low level actions with DB
     */
    @Before
    fun setup() {
        database = Room.inMemoryDatabaseBuilder(
            ApplicationProvider.getApplicationContext(),
            DictionaryDatabase::class.java
        ).allowMainThreadQueries()
            .build()
        dictionaryDao = database.dictionaryDao

    }

    @After
    fun tearDown() {
        database.close()
    }

    /**
     * Dictionary DAO actions
     */

    @Test
    fun `insert_group`() = runTest {
        val group = GroupEntity(
            groupId = UUID.randomUUID().toString(),
            name = "test group"
        )
        dictionaryDao.addNewGroup(group)

        dictionaryDao.selectAllGroup().test {
            val list = awaitItem()
            assert(list.contains(group))
            cancel()
        }
    }

    @Test
    fun `insert_word`() = runTest {

        val group = GroupEntity(
            groupId = TEST_GROUP_ID,
            name = TEST_GROUP_NAME
        )
        dictionaryDao.addNewGroup(group)

        val word = WordEntity(
            groupId = TEST_GROUP_ID,
            wordId = TEST_WORD_ID,
            nativeWord = TEST_WORD,
            translate = TEST_TRANSLATE,
            transcription = TEST_TRANSCRIPTION
        )
        dictionaryDao.addNewWord(word)

        dictionaryDao.selectGroupWithWords(TEST_GROUP_ID).test {
            val list = awaitItem()
            assert(list[0].words.contains(word))
            cancel()
        }
    }

    @Test
    fun `select_words_by_group_id`() = runTest {

        val group = GroupEntity(
            groupId = TEST_GROUP_ID,
            name = TEST_GROUP_NAME
        )
        dictionaryDao.addNewGroup(group)

        val word = WordEntity(
            groupId = TEST_GROUP_ID,
            wordId = TEST_WORD_ID,
            nativeWord = TEST_WORD,
            translate = TEST_TRANSLATE,
            transcription = TEST_TRANSCRIPTION
        )
        dictionaryDao.addNewWord(word)

        dictionaryDao.selectWordFromGroup(TEST_GROUP_ID).test {
            val list = awaitItem()
            assert(list.contains(word))
            cancel()
        }
    }

    @Test
    fun `delete_group`() = runTest {
        val group = GroupEntity(
            groupId = UUID.randomUUID().toString(),
            name = "test group"
        )
        dictionaryDao.addNewGroup(group)

        dictionaryDao.deleteGroup(group.groupId)

        dictionaryDao.selectAllGroup().test {
            val list = awaitItem()
            assert(list.isEmpty())
            cancel()
        }
    }

    @Test
    fun `delete_group_with_words`() = runTest {

        val group = GroupEntity(
            groupId = TEST_GROUP_ID,
            name = TEST_GROUP_NAME
        )
        dictionaryDao.addNewGroup(group)

        val word = WordEntity(
            groupId = TEST_GROUP_ID,
            wordId = TEST_WORD_ID,
            nativeWord = TEST_WORD,
            translate = TEST_TRANSLATE,
            transcription = TEST_TRANSCRIPTION
        )
        dictionaryDao.addNewWord(word)

        dictionaryDao.deleteGroup(TEST_GROUP_ID)

        dictionaryDao.selectAllWord().test {
            val list = awaitItem()
            assert(list.isEmpty())
            cancel()
        }
    }

    @Test
    fun `delete_word`() = runTest {

        val group = GroupEntity(
            groupId = TEST_GROUP_ID,
            name = TEST_GROUP_NAME
        )
        dictionaryDao.addNewGroup(group)

        val word = WordEntity(
            groupId = TEST_GROUP_ID,
            wordId = TEST_WORD_ID,
            nativeWord = TEST_WORD,
            translate = TEST_TRANSLATE,
            transcription = TEST_TRANSCRIPTION
        )
        dictionaryDao.addNewWord(word)

        dictionaryDao.deleteWord(TEST_WORD_ID)

        dictionaryDao.selectGroupWithWords(TEST_GROUP_ID).test {
            val list = awaitItem()
            assert(list[0].words.isEmpty())
            cancel()
        }
    }

    /**
     * Train DAO actions
     */
}