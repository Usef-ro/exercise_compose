package com.example.jetreader.Screens.login

import android.util.Log
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Button
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
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
import androidx.compose.ui.text.font.FontWeight
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

    val showLogin = rememberSaveable {
        mutableStateOf(true)
    }

    Surface(
        modifier = Modifier.fillMaxSize()
    ) {

        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Top
        ) {
            /*
            Logo
             */

            readerLogo()


            /*
            Form
             */
            if (showLogin.value)
                userForm(loading = false, isCreateAccount = showLogin.value) { email, pwd ->
                    Log.e("Inputss", "readerBookLogin: $email ,, $pwd")
                }
            else {
                userForm(loading = false, isCreateAccount = showLogin.value) { email, pwd ->

                }

            }

            Spacer(
                modifier = Modifier.height(15.dp),
            )

            /*
            Login or create
             */
            Row(
                modifier = Modifier.padding(15.dp),
                horizontalArrangement = Arrangement.Center,
                verticalAlignment = Alignment.CenterVertically
            ) {

                val text = if (showLogin.value) "Sign Up" else "Login"
                Text(text = "New User?")
                Text(
                    text = text, modifier = Modifier
                        .padding(10.dp)
                        .clickable {
                            showLogin.value = !showLogin.value
                        }, fontWeight = FontWeight.Bold,
                    color = MaterialTheme.colorScheme.secondary
                )
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

        if (isCreateAccount) {
            Text(
                text = "Please enter a valid email and password",
                modifier = Modifier.padding(4.dp)
            )
        } else {
            Text(text = "")
        }

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


        /*
        Button Submit
         */
        submitButton(
            textId = if (isCreateAccount) {
                "Create Account"

            } else {
                "Login"
            },
            loading = loading, validInput = valid
        ) {

            onDone(email.value.trim(), password.value.trim())
            keyboardController?.hide()

        }
    }
}


@Composable
fun submitButton(textId: String, loading: Boolean, validInput: Boolean, onClick: () -> Unit) {

    Button(
        onClick = onClick,
        modifier = Modifier
            .padding(3.dp)

            .fillMaxWidth(),
        enabled = !loading && validInput,
        shape = RoundedCornerShape(10.dp,10.dp,10.dp,10.dp),

    ) {
        if (loading) CircularProgressIndicator(
            modifier = Modifier
                .padding(10.dp)
                .size(25.dp)
        ) else Text(text = textId, modifier = Modifier
            .padding(2.dp)
            .clickable {

            })
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

