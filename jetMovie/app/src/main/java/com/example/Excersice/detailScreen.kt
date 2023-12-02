package com.example.Excersice

import android.annotation.SuppressLint
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Divider
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import coil.compose.AsyncImage
import coil.request.ImageRequest
import com.example.Excersice.domain.getMovie
import com.example.movieapp.R


@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@OptIn(ExperimentalMaterial3Api::class)
@Composable
@Preview
fun detailScreen(navController: NavController= rememberNavController(),
                 movieId: String?="helo"){

    val newMovieList= getMovie().filter {
        it.imdbID==movieId
    }

    Scaffold(
        topBar ={
            TopAppBar(title = {
                Text(text = "Movies")
            },
                modifier = Modifier.background(
                    Color.LightGray
                ), navigationIcon = {
                    Icon(
                        imageVector = Icons.Default.ArrowBack,
                        contentDescription = null,
                        Modifier.clickable {
                            navController.popBackStack()
                        }
                    )
                })


        }
    ) {

        Column(
            Modifier
                .fillMaxWidth()
                .fillMaxHeight()
            ,
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {

          movieRow(newMovieList.first())

            Text(text =newMovieList[0].title )

            Divider()

            Text(text ="Movie Images")

            LazyRow{
                items(newMovieList[0].images){

                    Card(
                        Modifier
                            .padding(12.dp)
                            .size(240.dp)
//                            , elevation = CardDefaults.elevatedShape
                    ) {

                        // coil
                        AsyncImage(
                            model = ImageRequest.Builder(LocalContext.current)
                                .data(it)
                                .crossfade(true)
                                .build(),
                            placeholder = painterResource(R.drawable.baseline_image_24),
                            contentDescription = "Poster",
                            contentScale = ContentScale.Crop,
                            modifier = Modifier.clip(CircleShape)
                        )

                    }
                }
            }

        }

    }

}