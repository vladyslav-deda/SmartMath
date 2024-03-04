package com.smartmath.splash

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.smartmath.splash.models.SplashContract
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SplashViewModel @Inject constructor() :ViewModel() {

    private val _authorizationState = Channel<SplashContract.Effect>()
    val authorizationState = _authorizationState.receiveAsFlow()

    init {
        viewModelScope.launch {
            delay(3000L)// checking is user authorized
            _authorizationState.send(SplashContract.Effect.OpenLoginScreen)
        }
    }
}