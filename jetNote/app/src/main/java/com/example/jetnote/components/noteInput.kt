package com.example.jetnote.components

import androidx.compose.foundation.background
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.text.input.ImeAction


@OptIn(ExperimentalMaterial3Api::class, ExperimentalComposeUiApi::class)
@Composable
fun noteInput(
    modifier: Modifier = Modifier,
    text: String,
    label: String,
    maxLine: Int = 1,
    onValueChanged: () -> Unit = {},
    onImActions: () -> Unit = {}
) {


    val keyboardController = LocalSoftwareKeyboardController.current


    TextField(

        modifier =
        modifier.background(Color.Transparent),
        value = text,
        onValueChange =
        { onValueChanged() },
        keyboardOptions = KeyboardOptions.Default.copy(
            imeAction = ImeAction.Done
        ),
        label = {
            Text(text = label)
        },
        keyboardActions = KeyboardActions(
            onDone = {
                onImActions()
                keyboardController?.hide()
            }
        )

    )


}


