package com.example.jettrivia.component

import android.util.Log
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.runtime.Composable
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.jettrivia.viewModel.viewModelQuestion


@Composable
fun triviaHome( viewModel:viewModelQuestion){

    val TAG = "triviaHome"
    val question=viewModel.data.value.data?.toMutableList()

    if(viewModel.data.value.loading==true){
        Log.e(TAG, "triviaHome: loading" )
        CircularProgressIndicator()
    }else{
        Log.e(TAG, "triviaHome: "+viewModel.data.value.data, )
    }


    Log.e(TAG, "data "+question )

}