package com.example.jetnote

import android.os.Build
import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.annotation.RequiresApi
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.tooling.preview.Preview
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.jetnote.ui.theme.JetNoteTheme
import androidx.compose.runtime.getValue
import androidx.compose.runtime.rememberCoroutineScope
import dagger.hilt.android.AndroidEntryPoint
import dagger.hilt.android.HiltAndroidApp
import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.launch


@AndroidEntryPoint

class MainActivity : ComponentActivity() {
    @RequiresApi(Build.VERSION_CODES.O)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            JetNoteTheme {

                /*
                ViewModel
                 */
                val viewmodel: noteViewModel = viewModel()

                val noteList=viewmodel.noteList.collectAsState().value

                /*
                Main Screen
                 */
                val composableScope = rememberCoroutineScope()

                noteScreen(
                    notes = noteList,
                    onAddNote = {
                        viewmodel.addNote(it)
                    }, onRemoveNote = {
                       composableScope.launch {
                           viewmodel.removeNote(it)
                       }
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