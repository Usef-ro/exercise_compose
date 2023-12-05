package com.example.jettrivia.viewModel

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.jettrivia.domain.modelQuestionItem
import com.example.jettrivia.repository.dataOrException
import com.example.jettrivia.repository.questionRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class viewModelQuestion @Inject constructor(
    val questionRepository: questionRepository
) : ViewModel() {

    val data: MutableState<dataOrException
    <ArrayList<modelQuestionItem>, Boolean, Exception>> = mutableStateOf(
        dataOrException(null, true, Exception(""))
    )

    init{
        getAllQuestions()
    }

    fun getAllQuestions(){
        viewModelScope.launch {
            data.value.loading=true
            data.value=questionRepository.getAllQuestion()

        if(data.value.data.toString().isNotEmpty()){
            data.value.loading=false

        }
        }
    }
    fun getTotalCount():Int{
        return data.value.data?.toMutableList()?.size!!
    }
}