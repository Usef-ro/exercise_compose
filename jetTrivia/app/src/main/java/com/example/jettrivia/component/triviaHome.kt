package com.example.jettrivia.component

import android.util.Log
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import com.example.jettrivia.viewModel.viewModelQuestion


@Composable
fun triviaHome(viewModel: viewModelQuestion) {

    val TAG = "triviaHome"
    val question = viewModel.data.value.data?.toMutableList()
    val questionIndex = remember {
        mutableStateOf(0)
    }
    if (viewModel.data.value.loading == true) {
        Log.e(TAG, "triviaHome: loading")
        CircularProgressIndicator()
    } else {


            val question = try {
                question?.get(questionIndex.value)
            } catch (e: Exception) {
                null
            }

            questionScreen(questionItem = question!!,questionIndex
           ,     viewModel = viewModel, onNextClicked = {
               questionIndex.value=questionIndex.value+1
                })


        Log.e(TAG, "triviaHome: " + viewModel.data.value.data)
    }


    Log.e(TAG, "data " + question)

}