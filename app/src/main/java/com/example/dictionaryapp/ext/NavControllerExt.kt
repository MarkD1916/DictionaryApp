package com.example.dictionaryapp.ext

import androidx.fragment.app.Fragment
import com.example.dictionaryapp.util.UiEvent


fun Fragment.navigateEvent(event: UiEvent.Navigate){
    requireActivity().supportFragmentManager
        .beginTransaction()
        .addToBackStack(null)
        .replace(event.fragmentContainer, event.fragment)
        .commit()
}