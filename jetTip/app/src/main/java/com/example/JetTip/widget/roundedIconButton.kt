package com.example.JetTip.widget

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp


val IconbuttonSizeModie: Modifier = Modifier.size(40.dp)

@Composable
fun roundedIconoButton(
    modifier: Modifier = Modifier,
    imageVector: ImageVector,
    onClick: () -> Unit,
    tint: Color = Color.Black.copy(alpha = 0.8f),
    // change dark mode change auto this line
    backgroundcolor: Color = MaterialTheme.colorScheme.background,
    elvation: Dp = 4.dp,


    ) {

    Card(
        modifier = modifier
            .padding(all = 4.dp)
            .background(
                backgroundcolor
            )
            /*
            both blow code is working
             */
//        .clickable  (onClick=onClick)
            .clickable { onClick.invoke() }
            .then(IconbuttonSizeModie),
        shape = CircleShape,
        elevation = CardDefaults.cardElevation(
            defaultElevation = elvation
        ),


        ) {
        Icon(

            modifier = Modifier.align(alignment = Alignment.CenterHorizontally),
            imageVector = imageVector,
            contentDescription = null,
            tint = tint
        )
    }
}