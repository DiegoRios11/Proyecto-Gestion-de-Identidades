package com.diegorios.vistatest.ui.logout

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class LogoutViewModel : ViewModel() {

    private val _text = MutableLiveData<String>().apply {
        value = "Presione para Cerrar Sesión"
    }
    val text: LiveData<String> = _text
}