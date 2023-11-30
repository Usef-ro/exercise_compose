package com.example.JetTip

import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.CornerSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Clear
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Slider
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.JetTip.components.InputField
import com.example.JetTip.ui.theme.ExcersiceTheme
import com.example.JetTip.util.calculateTipPercentage
import com.example.JetTip.widget.roundedIconoButton

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {

            myApp {

                MainContent()


            }
        }
    }
}


@Composable
fun myApp(content: @Composable () -> Unit) {
    ExcersiceTheme {

        content()
    }
}

//TopHeader

@Preview
@Composable
fun TopHeader(totalPerson: Double = 0.0) {
    Surface(
        modifier = Modifier
//        .clip(shape = RoundedCornerShape(corner= CornerSize(12.dp)))
            .clip(shape = CircleShape.copy(all = CornerSize(12.dp)))
            .fillMaxWidth()
            .height(150.dp),
//        color = Color(0xFFE9D7F7)
    ) {
        Column(
            modifier = Modifier.padding(21.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            val total = "%.2f".format(totalPerson)
            Text(text = "Total Per Person ", style = MaterialTheme.typography.bodyLarge)
            Text(
                text = "$$total",
                style = MaterialTheme.typography.bodyMedium, fontWeight = FontWeight.ExtraBold
            )
        }
    }
}


@Preview
@Composable
fun MainContent() {


    BillForm {
        Log.e("Bill", "MainContent: " + it)
    }

}

@OptIn(ExperimentalComposeUiApi::class)
@Composable
fun BillForm(
    modifier: Modifier = Modifier,
    onValChange: (String) -> Unit = {}
) {

    val totalState = remember {
        mutableStateOf("0")
    }

    val validState = remember(totalState.value) {
        totalState.value.trim().isNotEmpty()
    }

    val keyboardController =
        LocalSoftwareKeyboardController.current

    val sliderPositionState = remember {
        mutableStateOf(0f)
    }
    val splitByState = remember {
        mutableStateOf(1)
    }
    val range = IntRange(start = 1, endInclusive = 100)

    val tipPercentage = (sliderPositionState.value * 100).toInt()

    val tipAmountState = remember {
        mutableStateOf(0.0)
    }
    val totalPerPersonState= remember {
        mutableStateOf(0.0)
    }
    Column {
        TopHeader(totalPerson = totalPerPersonState.value)

        Surface(
            modifier = Modifier
                .padding(2.dp)
                .fillMaxWidth(),
            shape = RoundedCornerShape(corner = CornerSize(8.dp)),
            border = BorderStroke(width = 1.dp, color = Color.LightGray)
        ) {

            Column(
                modifier = Modifier.padding(6.dp),
                verticalArrangement = Arrangement.Top,
                horizontalAlignment = Alignment.Start
            ) {

                InputField(
                    modifier = Modifier.fillMaxWidth(),
                    valueState = totalState,
                    labelId = "Enter Bill",
                    enabled = true,
                    isSingleLine = true,
                    onAction = KeyboardActions {

                        if (!validState) return@KeyboardActions
                        onValChange(totalState.value.trim())
                        keyboardController?.hide()
                    }
                )
                if (validState) {

                    Row(
                        modifier = Modifier.padding(3.dp), horizontalArrangement = Arrangement.Start
                    ) {

                        Text(
                            "Split", modifier = Modifier.align(
                                alignment = Alignment.CenterVertically
                            )
                        )
                        Spacer(modifier = Modifier.width(120.dp))

                        Row(
                            modifier = Modifier.padding(horizontal = 3.dp),
                            horizontalArrangement = Arrangement.End
                        ) {

                            roundedIconoButton(
                                modifier = Modifier,
                                imageVector = Icons.Default.Clear,
                                onClick = {
                                    splitByState.value =
                                        if (splitByState.value > 1) splitByState.value
                                        else 1

                                    totalPerPersonState.value=
                                        calculateTotalPerPerson(totalState.value.toDouble(),
                                            splitByState.value,tipPercentage)

                                })

                            Text(
                                text = "${splitByState.value}",
                                modifier =
                                Modifier
                                    .padding(start = 9.dp, end = 9.dp)
                                    .align(alignment = Alignment.CenterVertically)
                            )

                            roundedIconoButton(
                                modifier = Modifier,
                                imageVector = Icons.Default.Add,
                                onClick = {

                                    if (splitByState.value < range.last) {
                                        splitByState.value += 1
                                        totalPerPersonState.value=
                                            calculateTotalPerPerson(totalState.value.toDouble(),
                                                splitByState.value,tipPercentage)
                                    }
                                })


                        }


                    }

                    // Tip Row
                    Row(modifier = Modifier) {
                        Text(
                            modifier = Modifier
                                .padding(start = 9.dp)
                                .align(alignment = Alignment.CenterVertically),
                            text = "Tip"
                        )

                        Spacer(modifier = Modifier.width(200.dp))

                        Text(
                            text = " $ ${tipAmountState.value}",
                            modifier = Modifier
                                .align(
                                    alignment =
                                    Alignment.CenterVertically
                                )
                                .padding(horizontal = 3.dp, vertical = 12.dp)
                        )

                    }

                    Column(
                        verticalArrangement = Arrangement.Center,
                        horizontalAlignment = Alignment.CenterHorizontally
                    ) {

                        Text("$tipPercentage %")

                        Spacer(modifier = Modifier.height(14.dp))


                        // Slider

                        Slider(
                            value = sliderPositionState.value,
                            onValueChange = {
                                sliderPositionState.value = it
                                tipAmountState.value =
                                    calculateTipPercentage(
                                        totalState.value.toDouble(), tipPercentage
                                    )
                            totalPerPersonState.value=
                                calculateTotalPerPerson(totalState.value.toDouble(),
                                    splitByState.value,tipPercentage)



                                            },
                            modifier = Modifier.padding(start = 16.dp, end = 16.dp),
                            steps = 5
                        )


                    }
                } else {
                    Box {

                    }
                }


            }
        }
    }

}

fun calculateTotalPerPerson(
    total:Double,
    splitBy:Int,
    tipPercentage:Int
):Double{
    val bill= calculateTipPercentage(total,tipPercentage = tipPercentage)+total

    return (bill/splitBy)
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    ExcersiceTheme {
        myApp {
        }
    }
}