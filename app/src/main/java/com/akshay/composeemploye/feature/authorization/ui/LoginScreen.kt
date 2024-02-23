package com.akshay.composeemploye.feature.authorization.ui

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Info
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.akshay.composeemploye.feature.authorization.presentation.LoginViewModel

@Composable
fun LoginScreen(
    onLoginSuccess: () -> Unit,
    loginViewModel: LoginViewModel = viewModel(),
    modifier: Modifier = Modifier
) {

    val loginUIState = loginViewModel.userLoginUiState

    Column(
        modifier = modifier
            .fillMaxSize()
            .background(color = Color.White),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {

        Text(
            text = "Login Screen",
            fontSize = 18.sp,
            color = Color.Black,
            modifier = Modifier.padding(top = 20.dp)
        )


        OutlinedTextField(
            value = loginUIState.username, onValueChange = {
                loginViewModel.onUserNameSet(it)
            },
            label = { Text(text = "Email") },
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Password),
            modifier = Modifier.padding(top = 10.dp),
            isError = loginUIState.userNameError.isNotBlank(),
            trailingIcon = {
                if (loginUIState.userNameError.isNotBlank()) {
                    Icon(Icons.Filled.Info, "error", tint = MaterialTheme.colorScheme.error)
                }
            },
            supportingText = {
                if (loginUIState.userNameError.isNotBlank()) {
                    Text(
                        modifier = Modifier.fillMaxWidth(),
                        text = loginUIState.userNameError,
                        color = MaterialTheme.colorScheme.error
                    )
                }
            }

        )
        OutlinedTextField(
            value = loginUIState.password, onValueChange = { loginViewModel.onPasswordSet(it) },
            label = { Text(text = "Password") },
            visualTransformation = PasswordVisualTransformation(),
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Password),
            modifier = Modifier.padding(top = 10.dp),
            isError = loginUIState.passwordError.isNotBlank(),
            trailingIcon = {
                if (loginUIState.passwordError.isNotBlank()) {
                    Icon(Icons.Filled.Info, "error", tint = MaterialTheme.colorScheme.error)
                }
            },
            supportingText = {
                if (loginUIState.passwordError.isNotBlank()) {
                    Text(
                        modifier = Modifier.fillMaxWidth(),
                        text = loginUIState.passwordError,
                        color = MaterialTheme.colorScheme.error
                    )
                }
            }
        )

        if (loginUIState.isLoading) {
            CircularProgressIndicator()
        } else {

            OutlinedButton(
                onClick = {
                    if (loginViewModel.validateCredentials()) {
                        loginViewModel.performLogin(onLoginSuccess)
                    }
                },
                colors = ButtonDefaults.buttonColors(containerColor = Color.DarkGray),
                modifier = Modifier
                    .fillMaxWidth(.7f)
                    .padding(top = 10.dp)
            ) {
                Text(text = "Login", color = Color.White)
            }
        }
    }
}

@Preview(backgroundColor = 0xfffff)
@Composable
fun LoginScreenPreview() {
    LoginScreen({})
}