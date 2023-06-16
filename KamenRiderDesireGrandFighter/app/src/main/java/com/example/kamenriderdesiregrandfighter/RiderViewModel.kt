package com.example.kamenriderdesiregrandfighter

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.kamenriderdesiregrandfighter.Model.KamenRider
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow

class RiderViewModel(kamenRider: KamenRider) : ViewModel() {
    private val _riderState = MutableStateFlow(kamenRider)
    val riderState: StateFlow<KamenRider> get() = _riderState

}