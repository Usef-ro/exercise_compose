package com.example.jettrivia.component

import androidx.compose.foundation.Canvas
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.ButtonDefaults.buttonColors
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.RadioButton
import androidx.compose.material3.RadioButtonDefaults
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.PathEffect
import androidx.compose.ui.text.ParagraphStyle
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextIndent
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.jettrivia.domain.modelQuestionItem
import com.example.jettrivia.viewModel.viewModelQuestion

@Composable
fun questionScreen(
    questionItem: modelQuestionItem,
    questionIndex: MutableState<Int>,
    viewModel: viewModelQuestion,
    onNextClicked: (Int) -> Unit = {},
) {

    val choicesState = remember(questionItem) {
        questionItem.choices.toMutableList()
    }

    val answerState = remember(questionItem) {
        mutableStateOf<Int?>(null)
    }

    val pathEffect = PathEffect.dashPathEffect(floatArrayOf(10f, 10f), 0f)

    val correctAnswerState = remember(questionItem) {
        mutableStateOf<Boolean?>(null)
    }

    val updateAnswer: (Int) -> Unit = remember(questionItem) {
        {
            answerState.value = it
            correctAnswerState.value = choicesState[it] == questionItem.answer
        }
    }

    Surface(
        modifier = Modifier
            .padding(4.dp)
            .fillMaxWidth()
            .fillMaxHeight(), color = MaterialTheme.colorScheme.primary
//        MaterialTheme.colorScheme.primary
    ) {

        Column(
            Modifier.padding(12.dp), verticalArrangement = Arrangement.Top,
            horizontalAlignment = Alignment.Start
        ) {

            /*
            Progress
             */
                if(questionIndex.value>=3) ShowProgress(questionIndex.value)

            /*
            Counter Questions
             */
            questionTracker(
                counter = questionIndex.value
            ,viewModel.getTotalCount()
            )


            /*
            Line Dotted
             */
            DrawDottedLine(pathEffect = pathEffect)


            /*
           Main Question
             */
            Column() {
                Text(
                    text = questionItem.question,
                    modifier = Modifier
                        .padding(6.dp)
                        .align(alignment = Alignment.Start)
                        .fillMaxHeight(0.3f),
                    fontSize = 17.sp,
                    fontWeight = FontWeight.Bold,
                    lineHeight = 22.sp,
                    color = Color.DarkGray
                )

                /*
                Question
                 */
                choicesState.forEachIndexed { index, answer ->
                    Row(
                        modifier = Modifier
                            .padding(3.dp)
                            .fillMaxWidth()
                            .height(45.dp)
                            .border(
                                width = 4.dp, brush = Brush.linearGradient(
                                    colors = listOf(Color.LightGray, Color.DarkGray)
                                ), shape = RoundedCornerShape(15.dp)

                            )
                            .clip(
                                RoundedCornerShape(
                                    50, 50, 50, 50
                                )
                            )
                            .background(Color.Transparent),
                        verticalAlignment = Alignment.CenterVertically

                    ) {

                        /*
                        Radio Button
                         */
                        RadioButton(
                            selected = (answerState.value == index), onClick = {
                                updateAnswer(index)
                            }, modifier = Modifier.padding(start = 16.dp),
                            colors = RadioButtonDefaults
                                .colors(
                                    selectedColor =
                                    if (correctAnswerState.value == true
                                        && index == answerState.value
                                    ) {
                                        Color.Green.copy(alpha = 0.2f)
                                    } else {
                                        Color.Red.copy(alpha = 0.2f)

                                    }
                                )
                        )

                        /*
                        Text Of Radio Button
                         */
                        val annotatedString = buildAnnotatedString {
                            withStyle(
                                style = SpanStyle(
                                    fontWeight = FontWeight.Light,
                                    color = if (correctAnswerState.value == true
                                        && index == answerState.value
                                    ) {
                                        Color.Green
                                    } else if (correctAnswerState.value == false &&
                                        index == answerState.value
                                    ) {
                                        Color.Red
                                    } else {
                                        Color.Gray
                                    },
                                    fontSize = 17.sp
                                )
                            ) {
                                append(answer)
                            }
                        }
                        Text(text = annotatedString, modifier = Modifier.padding(6.dp))
                    }
                }

                /*
                Button Next
                 */
                Button(
                    onClick = {
                        onNextClicked(questionIndex.value)
                    },
                    modifier = Modifier
                        .padding(3.dp)
                        .background(Color.White)
                        .align(alignment = Alignment.CenterHorizontally),
                    shape = RoundedCornerShape(34.dp),
                    colors = ButtonDefaults.buttonColors()
                ) {
                    Text(
                        text = "Next", modifier = Modifier
                            .padding(4.dp)
                            .background(Color.Black)
                    )
                }
            }
        }
    }
}


@Composable
fun questionTracker(
    counter: Int = 10, outOff: Int = 100
) {
    Text(text = buildAnnotatedString {
        withStyle(style = ParagraphStyle(textIndent = TextIndent.None)) {
            withStyle(
                style = SpanStyle(
                    color = Color.DarkGray,
                    fontWeight = FontWeight.Bold, fontSize = 27.sp
                )
            ) {

                append("Question $counter /")

                withStyle(
                    style = SpanStyle(
                        color = Color.DarkGray,
                        fontWeight = FontWeight.Light, fontSize = 14.sp
                    )
                ) {
                    append("$outOff")
                }
            }
        }
    }, modifier = Modifier.padding(20.dp))

}


@Composable
fun DrawDottedLine(
    pathEffect: PathEffect
) {
    Canvas(
        modifier = Modifier
            .fillMaxWidth()
            .height(1.dp)
    ) {
        drawLine(
            color = Color.DarkGray,
            start = Offset(0f, 0f),
            end = Offset(size.width, y = 0f),
            pathEffect = pathEffect
        )
    }
}

@Composable
fun ShowProgress(score: Int = 12) {

    val gradient = Brush.linearGradient(
        listOf(
            Color(0xFFF95075),
            Color(0xFFBE6BE5)
        )
    )


    val progressFactor= remember(score) {
        mutableStateOf(score*.005f)
    }

    Row(
        verticalAlignment = Alignment.CenterVertically,
        modifier = Modifier
            .padding(3.dp)
            .fillMaxWidth()
            .height(45.dp)
            .background(Color.Transparent)
            .border(
                width = 4.dp,
                brush = Brush.linearGradient(
                    colors = listOf(
                        Color.Red, Color.Green
                    )
                ), shape = RoundedCornerShape(34.dp)
            )
            .clip(
                RoundedCornerShape(
                    50, 50, 50, 50
                )
            )
    ) {
        Button(
            modifier = Modifier
                .fillMaxWidth(progressFactor.value)
                .background(brush = gradient),
            onClick = {},
            contentPadding = PaddingValues(1.dp),
            enabled = false,
            elevation = null,
            colors=buttonColors(
                containerColor = Color.Transparent,
                disabledContainerColor = Color.Transparent
            )
            ) {

            /*
            Text On Progress
             */
            Text(text=(score*10).toString(),
                modifier = Modifier.clip(
                    shape= RoundedCornerShape(23.dp)
                ).fillMaxWidth()
                    .fillMaxHeight(0.87f)
                    .padding(6.dp),
                color=Color.White
                , textAlign = TextAlign.Center
            )
        }
    }

}
