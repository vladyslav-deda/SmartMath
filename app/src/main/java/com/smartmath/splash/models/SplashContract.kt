package com.smartmath.splash.models

interface SplashContract {
    sealed interface Effect {
        object OpenLoginScreen: Effect

        object OpenMainScreen: Effect
    }
}