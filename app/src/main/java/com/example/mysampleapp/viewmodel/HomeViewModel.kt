package com.example.mysampleapp.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
private val savedStateHandle: SavedStateHandle
) : ViewModel() {

    private val _webViewUrl = MutableLiveData<String>()
    val webViewUrl: LiveData<String> = _webViewUrl

    init {
        // Load the initial URL
        _webViewUrl.value = "https://www.axisbank.com/"
    }

    fun updateUrl(newUrl: String) {
        _webViewUrl.value = newUrl
    }
}