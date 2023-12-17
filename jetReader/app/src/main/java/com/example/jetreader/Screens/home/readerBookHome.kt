package com.example.jetreader.Screens.home

import android.annotation.SuppressLint
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountCircle
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.rounded.Add
import androidx.compose.material3.Divider
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.scale
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.example.jetreader.Model.modelBook
import com.example.jetreader.Navigation.readerScreens
import com.example.jetreader.R
import com.google.firebase.auth.FirebaseAuth

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@OptIn(ExperimentalMaterial3Api::class)
@Composable
@Preview
fun readerBookHome(navController: NavController = rememberNavController()) {

    Scaffold(
        modifier = Modifier.fillMaxSize(),
        topBar = {
            readerAppBar(
                title = "Titleo",
//                accountName = "Joy",
                showProfile = true,
                navController = navController
            )
        },
        floatingActionButton = {
            FabContent() {

            }
        }
    ) {
        /*
        Content
         */
        Surface {
            HomeContent(navController = navController)
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun readerAppBar(
    title: String = "",
//                 accountName:String="",
    showProfile: Boolean = true,
    navController: NavController
) {
    TopAppBar(
        title = {
            Row(verticalAlignment = Alignment.CenterVertically) {
//             if(showProfile){
                Image(
                    modifier = Modifier
                        .size(35.dp)
                        .clip(RoundedCornerShape(12.dp))
                        .scale(0.6f),
                    painter = painterResource(id = R.drawable.person),
                    contentDescription = "person"
                )

//                    Text(text = accountName)

//             }else{
                Text(text = title)
                Spacer(modifier = Modifier.width(150.dp))
//             }
            }
        },
        actions = {
            IconButton(onClick = {
                FirebaseAuth.getInstance().signOut()
                    .run {
                        navController.navigate(readerScreens.loginScreen.name)
                    }
            }) {
                Icon(
                    imageVector = Icons.Rounded.Add, contentDescription = "Logout",
                    tint = Color.Green.copy(alpha = 0.4f)
                )
            }
        }, modifier = Modifier.background(Color.Transparent)
    )

}


@Composable
fun titleSection(
    modifier: Modifier = Modifier,
    labelId: String
) {
    Surface(
        modifier.padding(
            start = 5.dp,
            top = 1.dp
        )
    ) {
        Column {
            Text(
                text = labelId, fontSize = 19.sp, fontStyle = FontStyle.Normal,
                textAlign = TextAlign.Left
            )
        }
    }
}


@Composable
fun HomeContent(navController: NavController) {

    // me @gmail.com
    val currentUserNmae=if(!FirebaseAuth.getInstance().currentUser?.email.isNullOrEmpty())
        FirebaseAuth.getInstance().currentUser?.email
            ?.split("@")?.get(0) else
                "N/A"

    Column(
        Modifier.padding(2.dp),
        verticalArrangement = Arrangement.SpaceEvenly
    ) {

        Row(
            modifier = Modifier.align(alignment = Alignment.Start)
        ) {
            titleSection(labelId = "Your Reading \n " + "activity right now ...")

            Column {
                Icon(
                    modifier = Modifier
                        .clickable {
                            navController.navigate(readerScreens.statsScreen.name)
                        }
                        .size(45.dp),
                    imageVector = Icons.Filled.AccountCircle,
                    contentDescription = "",
                    tint = MaterialTheme.colorScheme.secondaryContainer
                )

                Spacer(modifier = Modifier.fillMaxWidth(0.7f))

                Text(
                    text = currentUserNmae!!,
                    modifier = Modifier.padding(2.dp), style = MaterialTheme.typography.bodyMedium,
                    color = Color.Red,
                    fontSize = 15.sp,
                    maxLines = 1,
                    overflow = TextOverflow.Clip
                )

                Divider()
            }
        }
    }
}


@Composable
fun readingRightNowArea(
    books: List<modelBook>,
    navController: NavController
) {


}

@Composable
fun FabContent(onTap: (String) -> Unit) {
    FloatingActionButton(
        onClick = { onTap("") }, shape = RoundedCornerShape(50.dp),
        modifier = Modifier.background(MaterialTheme.colorScheme.background)
    ) {

        Icon(
            imageVector = Icons.Default.Add,
            tint = MaterialTheme.colorScheme.secondary,
            contentDescription = "Add"
        )


    }
}
