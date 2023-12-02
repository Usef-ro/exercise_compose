package com.example.jetnote

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Notifications
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.jetnote.components.noteInput


@OptIn(ExperimentalMaterial3Api::class)
@Composable
@Preview
fun noteScreen(){

    Column(
        Modifier.padding(12.dp)
    ) {
        TopAppBar(title = {
            Text(text = "Note")
        }
       ,
        actions = {
            Icon(imageVector = Icons.Default.Notifications,contentDescription = null)
        }
        )
        Column(
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            noteInput(text = "Hi", label ="Enter" )
        }
    }

}