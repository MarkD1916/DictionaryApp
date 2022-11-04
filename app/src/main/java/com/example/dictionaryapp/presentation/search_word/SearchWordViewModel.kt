package com.example.dictionaryapp.presentation.search_word

import android.util.Log
import androidx.lifecycle.viewModelScope
import com.example.core.handler.Resource
import com.example.coreui.SetupVM
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch
import javax.inject.Inject


class SearchWordViewModel @Inject constructor(
    private val initialState: SearchWordState
) : SetupVM<SearchWordState, Resource<String>>(initialState) {
    var i = 0

    private val mState = MutableStateFlow("Hello")


    fun getWordsResult(query: String) {

        viewModelScope.launch {
            doTest()
        }

    }


    suspend fun doTest() {
        mState.map { it ->
            "$it Kotlin"
        }.collectLatest {
            val state = initialState.copy(
                word = it
            )
            updateUIState(state)
        }
//        doAction(Resource.Success(data = "${i++}"))
    }

}