package com.example.dictionaryapp.util

import androidx.fragment.app.Fragment

sealed class UiEvent {
    data class Navigate(
        val fragmentContainer:Int,
        val fragment: Fragment
        ): UiEvent()
}