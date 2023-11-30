package com.example.Excersice

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CornerSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountBox
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.Excersice.ui.theme.MovieAppTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {

            mainApp {
mainScreen()
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



@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@OptIn(ExperimentalMaterial3Api::class)
@Composable
private fun mainScreen() {

    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Text(text = "Movies")
                },
                modifier = Modifier.background(Color.Magenta),
            )
        },

        ) {
        Surface(
            color = MaterialTheme.colorScheme.background, modifier = Modifier.padding(it)
        ) {
            val i = listOf(
                "a", "b"
            )
            listItem(movieList = i)

        }
    }
}


@Composable
fun listItem(movieList: List<String>) {
    LazyColumn(
        modifier = Modifier.fillMaxHeight()
    ) {
        items(items = movieList, itemContent = { item ->

            movieRow(movie = item)


        })
    }

}


/*
Item Card
 */

@Composable
fun movieRow(movie: String) {
    Card(
        modifier =
        Modifier
            .padding(4.dp)
            .fillMaxWidth()
            .height(130.dp), shape = RoundedCornerShape(
            corner = CornerSize(16.dp),

            ), elevation = CardDefaults.cardElevation(defaultElevation = 10.dp)

    ) {

        Row(
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.Start,
            modifier = Modifier.fillMaxWidth(),
        ) {

            Surface(
                modifier =
                Modifier
                    .padding(12.dp)
                    .size(100.dp),
                shape = RectangleShape,
                shadowElevation = 4.dp
            ) {
                Icon(imageVector = Icons.Default.AccountBox, contentDescription = null)
            }
            Text(text = movie)
        }
    }
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    mainApp {

        mainScreen()
        
    }
}