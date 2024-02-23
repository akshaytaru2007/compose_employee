package com.akshay.composeemploye.feature.authorization.presentation

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.akshay.composeemploye.feature.authorization.state.LoginUIState
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

class LoginViewModel : ViewModel() {

    var userLoginUiState by mutableStateOf(LoginUIState())
    fun onUserNameSet(name: String) {
        userLoginUiState = userLoginUiState.copy(
            username = name
        )
    }

    fun onPasswordSet(password: String) {
        userLoginUiState = userLoginUiState.copy(password = password)
    }

    fun validateCredentials(): Boolean {
        var isValid = true

        userLoginUiState = userLoginUiState.copy(
            userNameError = if (userLoginUiState.username.isBlank()) {
                isValid = false

                "Enter valid username"
            } else "",
            passwordError = if (userLoginUiState.password.isBlank()) {
                isValid = false
                "Enter valid password"
            } else ""
        )

        return isValid
    }

    fun performLogin(onLoginSuccess: () -> Unit) {
        userLoginUiState = userLoginUiState.copy(
            isLoading = true
        )

        viewModelScope.launch {
            delay(2_000)
            userLoginUiState = userLoginUiState.copy(
                isLoading = false
            )
            onLoginSuccess()
        }
    }
}