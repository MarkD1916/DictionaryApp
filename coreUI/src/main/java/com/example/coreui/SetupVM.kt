package com.example.coreui


import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.Job
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch


abstract class SetupVM<State, Actions>(initialState: State): ViewModel() {

    var mJob: Job? = null

    private val _uiState = MutableStateFlow(initialState)
    val uiState: StateFlow<State> = _uiState.asStateFlow()

    fun updateUIState(state: State) {
        _uiState.update {
            state
        }
    }



    private val _actions = Channel<Actions>()
    val actions: Flow<Actions> = _actions.receiveAsFlow()

    fun doAction(action: Actions) {
        mJob?.cancel()
        mJob = viewModelScope.launch {
            _actions.send(action)
        }
    }

}