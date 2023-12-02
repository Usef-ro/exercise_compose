package com.example.jetnote

import android.os.Build
import android.util.Log
import androidx.annotation.RequiresApi
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Notifications
import androidx.compose.material3.Divider
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.jetnote.components.noteButton
import com.example.jetnote.components.noteInput
import com.example.jetnote.components.noteRow


@RequiresApi(Build.VERSION_CODES.O)
@OptIn(ExperimentalMaterial3Api::class)
@Composable

fun noteScreen(
    notes: List<com.example.jetnote.domain.Note>,
    onAddNote: (com.example.jetnote.domain.Note) -> Unit,
    onRemoveNote: (com.example.jetnote.domain.Note) -> Unit,
) {


    var title by remember {
        mutableStateOf("")
    }

    var description by remember {
        mutableStateOf("")
    }

    Column(
        Modifier.padding(12.dp)
    ) {
        TopAppBar(title = {
            Text(text = "Note")
        },
            actions = {
                Icon(imageVector = Icons.Default.Notifications, contentDescription = null)
            }
        )
        /*
        Content
         */
        Column(
            horizontalAlignment = Alignment.CenterHorizontally
        ) {


            noteInput(
                text = title,
                label = "Title", onValueChanged = {
                    if (it.all { char ->
                            char.isLetter() || char.isWhitespace()
                        }) title = it
                }
            )


            noteInput(
                text = description,
                label = "Des",
                onValueChanged = {
                    if (it.all { char ->
                            char.isLetter() || char.isWhitespace()
                        }) description = it
                }

            )

            noteButton(
                text = "Save",
                onClick = {

                }
            )

            Divider(
                modifier = Modifier.padding(
                    10.dp
                ),
            )

            LazyColumn {
                items(notes) { notee ->

                    noteRow(note = notee, onClick = {
                        Log.e("click item Note : ", "noteScreen: "+it )
                    })
                }
            }

        }
    }

}