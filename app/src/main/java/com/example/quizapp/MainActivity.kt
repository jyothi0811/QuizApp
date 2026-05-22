package com.example.quizapp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

class MainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            QuizScreen()
        }
    }
}

data class QuizQuestion(
    val question: String,
    val options: List<String>,
    val correctAnswer: String
)

@Composable
fun QuizScreen() {

    val questions = listOf(

        QuizQuestion(
            question = "What is Android?",
            options = listOf(
                "Browser",
                "Operating System",
                "Game",
                "Database"
            ),
            correctAnswer = "Operating System"
        ),

        QuizQuestion(
            question = "Which language is used for Android?",
            options = listOf(
                "Python",
                "Java",
                "Kotlin",
                "C++"
            ),
            correctAnswer = "Kotlin"
        ),

        QuizQuestion(
            question = "Who owns Android?",
            options = listOf(
                "Apple",
                "Google",
                "Microsoft",
                "Tesla"
            ),
            correctAnswer = "Google"
        ),

        QuizQuestion(
            question = "Which company developed Android Studio?",
            options = listOf(
                "Tesla",
                "Google",
                "Apple",
                "Meta"
            ),
            correctAnswer = "Google"
        ),

        QuizQuestion(
            question = "Jetpack Compose is used for?",
            options = listOf(
                "UI Design",
                "Gaming",
                "Database",
                "Networking"
            ),
            correctAnswer = "UI Design"
        )

    )

    var currentQuestionIndex by remember {
        mutableIntStateOf(0)
    }

    var score by remember {
        mutableIntStateOf(0)
    }

    var answerMessage by remember {
        mutableStateOf("")
    }

    var buttonClicked by remember {
        mutableStateOf(false)
    }

    val currentQuestion = questions[currentQuestionIndex]

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.White)
            .padding(20.dp),

        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {

        Text(
            text = "Quiz App",
            fontSize = 30.sp,
            color = Color.Black
        )

        Spacer(modifier = Modifier.height(30.dp))

        Text(
            text = currentQuestion.question,
            fontSize = 24.sp,
            color = Color.Black
        )

        Spacer(modifier = Modifier.height(20.dp))

        currentQuestion.options.forEach { option ->

            Button(
                onClick = {

                    if (!buttonClicked) {

                        if (option == currentQuestion.correctAnswer) {
                            score++
                            answerMessage = "Correct Answer"
                        } else {
                            answerMessage =
                                "Wrong Answer\nCorrect: ${currentQuestion.correctAnswer}"
                        }

                        buttonClicked = true
                    }
                },

                modifier = Modifier
                    .fillMaxWidth()
                    .padding(5.dp),

                colors = ButtonDefaults.buttonColors(
                    containerColor = Color(0xFF7E57C2)
                )

            ) {

                Text(
                    text = option,
                    color = Color.White
                )
            }
        }

        Spacer(modifier = Modifier.height(20.dp))

        Text(
            text = answerMessage,
            fontSize = 20.sp,
            color =
                if (answerMessage.contains("Correct"))
                    Color.Green
                else
                    Color.Red
        )

        Spacer(modifier = Modifier.height(20.dp))

        Button(
            onClick = {

                if (currentQuestionIndex < questions.size - 1) {

                    currentQuestionIndex++
                    answerMessage = ""
                    buttonClicked = false

                } else {

                    answerMessage =
                        "Quiz Finished!\nFinal Score: $score / ${questions.size}"
                }
            },

            colors = ButtonDefaults.buttonColors(
                containerColor = Color.Black
            )

        ) {

            Text(
                text =
                    if (currentQuestionIndex == questions.size - 1)
                        "Finish Quiz"
                    else
                        "Next Question",

                color = Color.White
            )
        }

        Spacer(modifier = Modifier.height(20.dp))

        if (answerMessage.contains("Quiz Finished")) {

            Button(
                onClick = {

                    currentQuestionIndex = 0
                    score = 0
                    answerMessage = ""
                    buttonClicked = false
                },

                colors = ButtonDefaults.buttonColors(
                    containerColor = Color.Blue
                )

            ) {

                Text(
                    text = "Restart Quiz",
                    color = Color.White
                )
            }
        }
    }
}