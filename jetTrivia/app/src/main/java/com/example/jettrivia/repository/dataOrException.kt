package com.example.jettrivia.repository

data class dataOrException<T,Boolean,E:Exception>(
    var data:T? =null,
    var loading:Boolean? =null,
    var e:E? =null
)