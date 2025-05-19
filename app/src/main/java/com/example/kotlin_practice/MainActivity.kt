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


class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            KotlinpracticeTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    Greeting(
                        name = "Android",
                        modifier = Modifier.padding(innerPadding)

                    )
                }
            }
        }

    }
}


@Composable
fun Greeting(name: String, modifier: Modifier = Modifier) {
    var message by remember { mutableStateOf("押せよ") }
    var text by remember { mutableStateOf("") }

    Column(modifier = modifier.padding(16.dp)) {
        Text(text = "こんにちは!", color = Color.Red)

        Button(
            onClick = { Log.d("Button", "onClick")
                        message = "クリックされた！"
                      },

            modifier = Modifier.padding(top = 20.dp)
        ) {
            Text(message)
        }

        TextField(
            value = text,
            onValueChange = { text = it }
        )
    }
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    KotlinpracticeTheme {
        Greeting("Android")
    }
}