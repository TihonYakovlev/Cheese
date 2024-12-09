package com.example.cheese.view_models

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update

data class AuthScreenState(
    val currentScreen: String = "phone_input",
    val isErrorCode: Boolean = false
)

class AuthorizationViewModel : ViewModel() {

    private val _screenState = MutableStateFlow(AuthScreenState())
    val screenState: StateFlow<AuthScreenState>
        get() = _screenState.asStateFlow()

    fun changeScreen(screen: String) {
        _screenState.update {
            state ->
            state.copy(
                currentScreen = screen,
                isErrorCode = false
            )
        }
    }
}