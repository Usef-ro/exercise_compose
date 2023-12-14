package com.example.jetreader.Screens.login.viewModel

data class LoadingState(
    val status:Status,val message:String?=null
){

    companion object{
        val Idle=LoadingState(Status.Idle)
        val Success=LoadingState(Status.Success)
        val Failed=LoadingState(Status.Failed)
        val Loading=LoadingState(Status.Loading)
    }
    enum class Status {
        Success,
        Failed,
        Loading,
        Idle
    }
}
