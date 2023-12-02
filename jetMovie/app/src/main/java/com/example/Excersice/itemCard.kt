package com.example.Excersice

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.animateContentSize
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.CornerSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.KeyboardArrowDown
import androidx.compose.material.icons.filled.KeyboardArrowUp
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Divider
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import coil.request.ImageRequest
import com.example.Excersice.domain.getMovie
import com.example.Excersice.domain.movieModel
import com.example.movieapp.R

/*
Item Card
 */

@Composable
@Preview
fun movieRow(
    movie: movieModel = getMovie()[0], onItemClick: (String) -> Unit = {}
) {
    /*
    Must be called by in this remember
     */
    var expanded by remember {
        mutableStateOf<Boolean>(false)
    }

    Card(
        modifier =
        Modifier
            .padding(4.dp)
            .fillMaxWidth()
            .clickable {
                onItemClick(movie.imdbID)
            }
            .height(130.dp),
        shape = RoundedCornerShape(
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

//                Icon(imageVector = Icons.Default.AccountBox, contentDescription = null)

                // coil
                AsyncImage(
                    model = ImageRequest.Builder(LocalContext.current)
                        .data(movie.poster)
                        .crossfade(true)
                        .build(),
                    placeholder = painterResource(R.drawable.baseline_image_24),
                    contentDescription = "Poster",
                    contentScale = ContentScale.Crop,
                    modifier = Modifier.clip(CircleShape)
                )

            }

            Column(
//                Modifier.height(150.dp)
            ) {

                Text(
                    text = movie.actors,
                    style = MaterialTheme.typography.titleLarge
                )

                Text(
                    text = "Directory: ${movie.director}",
                    style = MaterialTheme.typography.titleSmall
                )

                Text(
                    text = "Year : ${movie.year}",
                    style = MaterialTheme.typography.titleSmall
                )

                AnimatedVisibility(visible = expanded) {
                    Column(

//                        modifier = Modifier.animateContentSize()

                    ) {
                        
                        /*
                        
                        This Work But not Show icon dropDown and not set style 
                        
                         */
                        
//                        Text(buildAnnotatedString {
//                            withStyle(
//                                style = SpanStyle(
//                                    color = Color.DarkGray, fontSize = 10.sp
//                                )
//                            ) {
//                                Text(text = "Plot:")
//                            }
//                            withStyle(
//                                style = SpanStyle(
//                                    color = Color.DarkGray,
//                                    fontSize = 10.sp,
//                                    fontWeight = FontWeight.Bold
//                                )
//                            ) {
//                                Text(text = movie.metascore)
//                            }
//                        })

                        Divider()

                        Text(
                            text = "Director : ${movie.imdbRating}",
                            fontSize = 10.sp,
                            style = MaterialTheme.typography.titleSmall
                        )
                        Text(
                            text = "Actors : ${movie.actors}", fontSize = 10.sp,
                            style = MaterialTheme.typography.titleSmall
                        )
                        Text(
                            text = "imdbRating : ${movie.response}", fontSize = 10.sp,
                            style = MaterialTheme.typography.titleSmall
                        )


//                        Spacer(modifier = Modifier.height(10.dp))


                    }
                }

                Icon(imageVector = if (expanded) Icons.Default.KeyboardArrowDown
                else Icons.Default.KeyboardArrowUp, contentDescription = "more",
                    modifier = Modifier
                        .size(20.dp)
                        .clickable {
                            expanded = !expanded
                        }
                )
            }

        }
    }
}


