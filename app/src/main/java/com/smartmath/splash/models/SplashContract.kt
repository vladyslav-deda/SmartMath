package com.smartmath.splash.models

interface SplashContract {
    sealed interface Effect {
        data object OpenLoginScreen: Effect

        data object OpenMainScreen: Effect
    }
}