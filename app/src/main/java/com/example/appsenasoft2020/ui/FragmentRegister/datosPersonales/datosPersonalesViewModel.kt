package com.example.appsenasoft2020.ui.FragmentRegister.datosPersonales

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class datosPersonalesViewModel : ViewModel() {

    private val _text = MutableLiveData<String>().apply {
        value = "This is rc Fragment"
    }
    val text: LiveData<String> = _text
}