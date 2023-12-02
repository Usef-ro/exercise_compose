package com.example.Excersice

import android.util.Log
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
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
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.Excersice.domain.getMovie
import com.example.Excersice.domain.movieModel
import com.example.Excersice.navigation.movieScreen

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomeScreen(navController: NavController) {

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

            listItem(movieList = getMovie(), navController = navController)

        }
    }
}


@Composable
fun listItem(movieList: List<movieModel>, navController: NavController) {
    LazyColumn(
        modifier = Modifier.fillMaxHeight()
    ) {
        items(items = movieList, itemContent = { item ->

            movieRow(movie = item) {
                Log.e("Item Click", "listItem: " + it)
                navController.navigate(
                    route=movieScreen.detailsScreen.name+"/$it"
                )
            }


        })
    }

}


