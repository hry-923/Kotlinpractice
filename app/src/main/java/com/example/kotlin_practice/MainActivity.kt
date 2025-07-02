package com.example.kotlin_practice

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.example.kotlin_practice.ui.theme.KotlinpracticeTheme
import androidx.compose.ui.graphics.Color
import android.util.Log
import androidx.compose.runtime.remember
import androidx.compose.ui.unit.dp
import androidx.compose.runtime.getValue
import androidx.compose.runtime.setValue
import androidx.compose.material3.TextField
import androidx.compose.runtime.mutableStateOf
import androidx.compose.foundation.layout.Column
import androidx.compose.material3.BottomAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults


class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            KotlinpracticeTheme {
                MyScreen()
            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MyScreen() {
    Scaffold (
        topBar = {
            TopAppBar(
                title = { Text("ヘッダー") },
                colors = TopAppBarDefaults.topAppBarColors(
                    containerColor = Color(0xFF1976D2),
                    titleContentColor = Color.White
                )
            )
        },
        bottomBar = {
            BottomAppBar (
                containerColor = Color(0xFF388E3C)
            ) {
                Text("フッター",
                    modifier = Modifier.padding(16.dp),
                    color = Color.White
                )
            }
        }
    ) { innerPadding ->
        Greeting (
            name = "Android",
            modifier = Modifier.padding(innerPadding)
        )
    }
}

@Composable
fun Greeting(name: String, modifier: Modifier = Modifier) {
    var message by remember { mutableStateOf("押すなよ") }
    var output  by remember { mutableStateOf("") }
    var text    by remember { mutableStateOf("") }

    var heightText by remember { mutableStateOf("") }
    var weightText by remember { mutableStateOf("") }
    var bmiResult by remember { mutableStateOf("") }

    Column(modifier = modifier.padding(16.dp)) {
        Text(text = "こんにちは!", color = Color.Red)

        Button(
            onClick = { Log.d("Button", "onClick")
                        message = "押すなって言ったやん"
                        output  = "お前も韓国語を勉強しろ"
                      },

            modifier = Modifier.padding(top = 20.dp)
        ) {
            Text(message)
        }
        Text(text = output, color = Color.Blue)

        TextField(
            value = text,
            onValueChange = { text = it },
            label = { Text("自由入力") },
            modifier = Modifier.padding(top = 20.dp)
        )

        // BMI計算用のUI
        Text(text = "BMI計算", color = Color.Black, modifier = Modifier.padding(top = 30.dp))

        TextField(
            value = heightText,
            onValueChange = { heightText = it },
            label = { Text("身長 (cm)") },
            modifier = Modifier.padding(top = 8.dp)
        )

        TextField(
            value = weightText,
            onValueChange = { weightText = it },
            label = { Text("体重 (kg)") },
            modifier = Modifier.padding(top = 8.dp)
        )

        Button(
            onClick = {
                val height = heightText.toFloatOrNull()
                val weight = weightText.toFloatOrNull()

                if (height != null && weight != null && height > 0) {
                    val heightInMeters = height / 100
                    val bmi = weight / (heightInMeters * heightInMeters)
                    bmiResult = "あなたのBMIは %.2f です".format(bmi)
                } else {
                    bmiResult = "正しい数値を入力してください"
                }
            },
            modifier = Modifier.padding(top = 16.dp)
        ) {
            Text("BMIを計算する")
        }

        Text(text = bmiResult, color = Color.Magenta, modifier = Modifier.padding(top = 16.dp))
    }
}