package com.example.jettrivia.domain


import com.google.gson.annotations.SerializedName

data class modelQuestionItem(
    @SerializedName("answer")
    val answer: String,
    @SerializedName("category")
    val category: String,
    @SerializedName("choices")
    val choices: List<String>,
    @SerializedName("question")
    val question: String
)