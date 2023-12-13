package com.example.jetreader.Components

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.jetreader.R

@Composable
fun readerLogo(modifier: Modifier = Modifier) {
    Text(
        modifier = modifier.padding(16.dp),
        text = " A . Reader",
        style = MaterialTheme.typography.bodyMedium,
        color = Color.Red.copy(alpha = 0.5f)
    )

}


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun InputField(
    modifier: Modifier = Modifier,
    valueState: MutableState<String>,
    lablelId: String,
    enable: Boolean,
    isSingleLine: Boolean = true,
    keyboardType: KeyboardType = KeyboardType.Text,
    imeAction: ImeAction = ImeAction.Next,

    onAction: KeyboardActions = KeyboardActions.Default
) {


    OutlinedTextField(
        value = valueState.value,
        label = { Text(text = lablelId) },
        singleLine = isSingleLine,
        textStyle = TextStyle(
            fontSize = 18.sp, color = MaterialTheme.colorScheme.onBackground
        ),
        modifier = modifier
            .padding(
                bottom = 10.dp, start = 10.dp,
                end = 10.dp
            )
            .fillMaxWidth(),
        enabled = enable,
        keyboardOptions = KeyboardOptions(keyboardType = keyboardType, imeAction = imeAction),
        onValueChange = { valueState.value = it },
    )

}


@Composable
@OptIn(ExperimentalMaterial3Api::class)
fun passwordInput(
    modifier: Modifier,
    passwordState: MutableState<String>,
    labelId: String, enable: Boolean,
    passwordVisibility: MutableState<Boolean>,
    imeAction: ImeAction = ImeAction.Done,
    onAction: KeyboardActions
) {
    val visualTransformation = if (passwordVisibility.value) VisualTransformation.None else
        PasswordVisualTransformation()

    OutlinedTextField(value = passwordState.value, onValueChange = {
        passwordState.value = it
    }, label = {
        Text(text = labelId)
    }, singleLine = true,
        textStyle = TextStyle(
            fontSize = 18.sp,
            color = MaterialTheme.colorScheme.onBackground
        ), modifier = modifier
            .padding(10.dp, 10.dp, 10.dp)
            .fillMaxWidth(), enabled = enable,
        keyboardOptions = KeyboardOptions(
            keyboardType = KeyboardType.Password,
            imeAction = imeAction,

            ),

        visualTransformation = visualTransformation,
        trailingIcon = {
            PasswordVisibility(passwordVisibility = passwordVisibility)
        },
        keyboardActions = onAction
    )


}


@Composable
fun PasswordVisibility(passwordVisibility: MutableState<Boolean>) {
    val visible = passwordVisibility.value
    IconButton(onClick = { passwordVisibility.value = !visible }) {
        Icon(
            painter = painterResource(id = R.drawable.baseline_remove_red_eye_24),
            contentDescription = "show pwd"
        )
    }
}
