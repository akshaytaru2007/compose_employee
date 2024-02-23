package com.akshay.composeemploye.feature.authorization.state

import androidx.compose.runtime.saveable.mapSaver

data class LoginUIState(
    var username: String = "",
    var password: String = "",
    var userNameError: String = "",
    var passwordError: String = "",
    var isLoading: Boolean = false
) {


    //Ignore following, I added this for study or future reference purpose.
    //on how to create Custom savable
    companion object {
        val saver = mapSaver(
            save = { loginUIState: LoginUIState ->
                mapOf(
                    "username" to loginUIState.username,
                    "password" to loginUIState.password,
                    "isUserNameError" to loginUIState.userNameError,
                    "isPasswordError" to loginUIState.passwordError,
                )
            },
            restore = {
                LoginUIState(
                    it["username"] as String,
                    it["password"] as String,
                    it["isUserNameError"] as String,
                    it["isPasswordError"] as String,
                )

            }
        )
    }
}