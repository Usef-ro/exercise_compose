package com.example.Excersice

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import com.example.Excersice.navigation.setupNavigation
import com.example.Excersice.ui.theme.MovieAppTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {

            mainApp {
                setupNavigation()
            }
        }
    }


}


@Composable
fun mainApp(content: @Composable () -> Unit) {
    MovieAppTheme {

        content()


    }
}


@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    mainApp {

        setupNavigation()

    }
}