package com.example.jetpackcomposeapplication

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class DynamicContentViewModel : ViewModel() {
    val textFieldState = MutableLiveData("")

    fun onTextChanged(newText: String) {
        textFieldState.value = newText
    }
}
