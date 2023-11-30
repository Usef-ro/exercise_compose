package com.example.JetTip.util

fun calculateTipPercentage(totalBill: Double, tipPercentage: Int): Double {

    return if(totalBill>1 && totalBill.toString().isNotEmpty()){
        (totalBill*tipPercentage)/100
    }else{
        0.0
    }
}