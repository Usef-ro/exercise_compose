package com.example.jettrivia.repository

import android.util.Log
import com.example.jettrivia.domain.modelQuestion
import com.example.jettrivia.domain.modelQuestionItem
import java.io.IOException
import javax.inject.Inject

class questionRepository @Inject constructor(
    val api:questionApi
) {

    val TAG = "questionRepository"
    val listOfQuestion=
      dataOrException <ArrayList<modelQuestionItem>
    ,Boolean,Exception>()


    suspend fun getAllQuestion():
            dataOrException<ArrayList<modelQuestionItem>,Boolean,Exception>{
        try{
            listOfQuestion.loading=true

            listOfQuestion.data=api.getAllQuestion()
            if(listOfQuestion.data.toString().isNotEmpty())

                listOfQuestion.loading=false

        }catch (exception:IOException){

            listOfQuestion.e=exception

            Log.e(TAG, "getAllQuestion: ${listOfQuestion.e!!.localizedMessage}" )
        }
        return listOfQuestion
    }


}