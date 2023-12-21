package com.example.jettrivia.component

import android.util.Log
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.size
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.example.jettrivia.viewModel.viewModelQuestion


@Composable
fun triviaHome(viewModel: viewModelQuestion) {

    val TAG = "triviaHome"
    val question = viewModel.data.value.data?.toMutableList()
    val questionIndex = remember {
        mutableStateOf(0)
    }


    
    if (viewModel.data.value.loading == true && null != viewModel.data.value.e) {
        Log.e(TAG, "triviaHome: ${viewModel.data.value.e}", )
        Log.e(TAG, "triviaHome: loading")

        Column(
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally, modifier = Modifier.fillMaxSize()
        ) {

            CircularProgressIndicator(
//                modifier = Modifier.size(150.dp)
            )

            Spacer(modifier = Modifier.height(20.dp))
            
            Text(
                text = "JetTrivia",
                modifier = Modifier.size(height = 20.dp, width = 75.dp),
                textAlign = TextAlign.Center,
                style = TextStyle(color = Color.Black, fontWeight = FontWeight.Bold)
            )
        }


    } else {


        val question = try {
            question?.get(questionIndex.value)
        } catch (e: Exception) {
            null
        }

        questionScreen(
            questionItem = question!!,
            questionIndex,
            viewModel = viewModel,
            onNextClicked = {
                questionIndex.value = questionIndex.value + 1
            })


        Log.e(TAG, "triviaHome: " + viewModel.data.value.data)
    }


    Log.e(TAG, "data " + question)

}