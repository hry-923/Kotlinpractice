package com.example.kotlin_practice // または com.example.kotlin_practice.ui.screens など

import android.util.Log
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.Settings
import androidx.compose.material3.BottomAppBar
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.kotlin_practice.ui.theme.KotlinpracticeTheme // Themeのインポートがここでも必要になる場合がある (Preview用)

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MyScreen(
    onNavigateToSettings: () -> Unit,
    onNavigateToProfile: () -> Unit
) {
    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Top") },
                actions = {
                    IconButton(onClick = onNavigateToProfile) {
                        Icon(
                            imageVector = Icons.Filled.Person,
                            contentDescription = "Profileへ",
                            tint = Color.Black
                        )
                    }
                    IconButton(onClick = onNavigateToSettings) {
                        Icon(
                            imageVector = Icons.Filled.Settings,
                            contentDescription = "Settingsへ",
                            tint = Color.Black
                        )
                    }
                }
            )
        },
        bottomBar = {
            BottomAppBar(
                containerColor = Color(0xFF388E3C)
            ) {
                Text(
                    "フッター", modifier = Modifier.padding(16.dp), color = Color.White
                )
            }
        }
    ) { innerPadding ->
        Greeting( // Greeting関数をMyScreen内で呼び出し
            modifier = Modifier.padding(innerPadding)
        )
    }
}

@Composable
fun Greeting(modifier: Modifier = Modifier) {
    var message by remember { mutableStateOf("押すなよ") }
    var output by remember { mutableStateOf("") }
    var text by remember { mutableStateOf("") }

    var heightText by remember { mutableStateOf("") }
    var weightText by remember { mutableStateOf("") }
    var bmiResult by remember { mutableStateOf("") }

    Column(
        modifier = modifier
            .padding(16.dp) // この画面固有の追加padding
            .fillMaxSize()
    ) {
        Text(text = "こんにちは!", color = Color.Red)

        Button(
            onClick = {
                Log.d("Button", "onClick")
                message = "押すなって言ったやん"
                output = "お前も韓国語を勉強しろ"
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
            }, modifier = Modifier.padding(top = 16.dp)
        ) {
            Text("BMIを計算する")
        }

        Text(text = bmiResult, color = Color.Magenta, modifier = Modifier.padding(top = 16.dp))
    }
}

@Preview(showBackground = true)
@Composable
fun MyScreenPreview() {
    KotlinpracticeTheme {
        MyScreen(onNavigateToSettings = {}, onNavigateToProfile = {})
    }
}
