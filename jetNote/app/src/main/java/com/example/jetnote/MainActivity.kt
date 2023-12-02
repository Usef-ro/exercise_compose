package com.example.jetnote

import android.os.Build
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.annotation.RequiresApi
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.jetnote.ui.theme.JetNoteTheme
import androidx.compose.runtime.getValue


class MainActivity : ComponentActivity() {
    @RequiresApi(Build.VERSION_CODES.O)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            JetNoteTheme {

                val viewmodel: noteViewModel by viewModel()

                val noteList = viewmodel.getAllNotes()

                noteScreen(
                    notes = noteList,
                    onAddNote = {
                        viewmodel.addNote(it)
                    }, onRemoveNote = {
                        viewmodel.removeNote(it)
                    }
                )

            }
        }
    }
}


@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    JetNoteTheme {

    }
}