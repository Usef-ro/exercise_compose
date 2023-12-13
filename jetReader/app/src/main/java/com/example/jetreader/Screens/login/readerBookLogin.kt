package com.example.jetreader.Screens.login

import android.util.Log
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.focus.focusRequester
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.jetreader.Components.InputField
import com.example.jetreader.Components.passwordInput
import com.example.jetreader.Components.readerLogo


/*
Reader Login
 */
@Composable
fun readerBookLogin(navController: NavController) {

    Surface(
        modifier = Modifier.fillMaxSize()
    ) {

        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Top
        ) {
            readerLogo()
            userForm(isCreateAccount = false) { email, pwd ->
                Log.e("Inputss", "readerBookLogin: $email ,, $pwd")
            }
        }
    }
}


/*
User Form
 */



@OptIn(ExperimentalComposeUiApi::class)
@Composable
fun userForm(
    loading: Boolean = false,
    isCreateAccount: Boolean = false,
    onDone: (String, String) -> Unit = { email: String, pwd: String -> }
) {
    val email = rememberSaveable {
        mutableStateOf("")
    }
    val password = rememberSaveable {
        mutableStateOf("")
    }
    val passwordVisibility = rememberSaveable {
        mutableStateOf(false)
    }

    val passwordFocus = FocusRequester.Default

    val keyboardController = LocalSoftwareKeyboardController.current

    val valid = remember(email.value, password.value) {
        email.value.trim().isNotEmpty() && password.value.trim().isNotEmpty()
    }

    Column(
        modifier =
        Modifier
            .height(250.dp)
            .background(MaterialTheme.colorScheme.background)
            .verticalScroll(rememberScrollState()),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {

        /*
        Email
         */

        emailInput(emailState = email, enable = !loading,
            onAction = KeyboardActions {
                passwordFocus.requestFocus()
            })


        /*
        Password
         */
        passwordInput(
            modifier = Modifier.focusRequester(passwordFocus), passwordState = password,
            labelId = "Password",
            enable = !loading,
            passwordVisibility = passwordVisibility,
            onAction = KeyboardActions {
                if (!valid) return@KeyboardActions
                onDone(email.value.trim(), password.value.trim())
            }
        )

    }
}


/*
        Email Input
 */


@Composable
fun emailInput(
    modifier: Modifier = Modifier,
    emailState: MutableState<String>,
    labelId: String = "Email",
    enable: Boolean = true,
    imeAction: ImeAction = ImeAction.Next,
    onAction: KeyboardActions = KeyboardActions.Default
) {


    InputField(
        modifier = modifier, valueState = emailState,
        enable = enable, imeAction = imeAction,
        keyboardType = KeyboardType.Email,
        onAction = onAction, lablelId = labelId
    )

}

