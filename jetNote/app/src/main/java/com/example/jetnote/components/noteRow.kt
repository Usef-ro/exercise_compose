package com.example.jetnote.components

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.example.jetnote.domain.Note
import java.time.format.DateTimeFormatter
import java.util.UUID

@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun noteRow(
    modifier: Modifier=Modifier,
    note:Note,
    onClick:(Note)->Unit={}
){


    Surface(
        modifier
            .padding(4.dp)
            .clip(
                RoundedCornerShape(
                    topEnd = 33.dp, bottomStart = 33.dp,
                )
            )
            .fillMaxWidth(),
        color= Color(0xFFDFE6EB),
        shadowElevation = 6.dp
    ) {

        Column(
            modifier
                .clickable {

                }
                .padding(horizontal = 14.dp, vertical = 6.dp)
        , horizontalAlignment = Alignment.Start
        ) {

            Text(text =note.title,
                style=MaterialTheme.typography.titleSmall)

            Text(text = note.description,style=MaterialTheme.typography.labelSmall  )

            Text(
                text = note.entry.toString(),
                // Jul 18 ,Wed, Jul 18 1999

                style=MaterialTheme.typography.labelSmall
                )
        }

    }

}