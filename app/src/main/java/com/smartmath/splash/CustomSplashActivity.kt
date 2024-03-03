package com.smartmath.splash

import android.content.Intent
import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import com.smartmath.MainActivity
import com.smartmath.databinding.ActivitySplashBinding
import com.smartmath.splash.models.SplashContract
import kotlinx.coroutines.launch

class CustomSplashActivity : AppCompatActivity() {

    private val loginViewModel by viewModels<CustomSplashViewModel>()
    private lateinit var binding: ActivitySplashBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivitySplashBinding.inflate(layoutInflater)
        setContentView(binding.root)
        subscribeToAuthUserState()
    }

    private fun subscribeToAuthUserState() {
        lifecycleScope.launch {
            repeatOnLifecycle(Lifecycle.State.RESUMED) {
                loginViewModel.authorizationState.collect(::handleAuthorizationState)
            }
        }
    }

    private fun handleAuthorizationState(state: SplashContract.Effect){
        val intent = when(state) {
            SplashContract.Effect.OpenLoginScreen -> {
                Intent(this, MainActivity::class.java)
            }
            SplashContract.Effect.OpenMainScreen -> {
                Intent(this, MainActivity::class.java)
            }
        }
        startActivity(intent)
        finishAffinity()
    }
}