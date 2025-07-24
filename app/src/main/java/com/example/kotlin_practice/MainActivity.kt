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
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.Settings
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController


class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            KotlinpracticeTheme {
                // NavControllerを初期化
                val navController = rememberNavController()
                NavHost(navController = navController, startDestination = "main") {
                    composable("main") {
                        MyScreen(onNavigateToSettings = {
                            navController.navigate("settings")
                        })
                    }
                    composable("settings") {
                        SettingsScreen(onNavigateUp = {
                            navController.popBackStack()
                        })
                    }
                }
            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MyScreen(onNavigateToSettings: () -> Unit) { // 設定画面へのナビゲーションコールバックを追加
    Scaffold(topBar = {
        TopAppBar(
            title = { Text("ヘッダー") }, colors = TopAppBarDefaults.topAppBarColors(
                containerColor = Color(0xFF1976D2), titleContentColor = Color.White
            ), actions = { // TopAppBarに設定アイコンボタンを追加
                IconButton(onClick = onNavigateToSettings) {
                    Icon(
                        imageVector = Icons.Filled.Settings,
                        contentDescription = "設定画面へ",
                        tint = Color.White // アイコンの色を白に指定
                    )
                }
            })
    }, bottomBar = {
        BottomAppBar(
            containerColor = Color(0xFF388E3C)
        ) {
            Text(
                "フッター", modifier = Modifier.padding(16.dp), color = Color.White
            )
        }
    }) { innerPadding ->
        Greeting(
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
            .padding(16.dp)
            .fillMaxSize()
    ) { // fillMaxSizeを追加してコンテンツが画面全体に広がるように
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
            }, modifier = Modifier.padding(top = 16.dp)
        ) {
            Text("BMIを計算する")
        }

        Text(text = bmiResult, color = Color.Magenta, modifier = Modifier.padding(top = 16.dp))
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SettingsScreen(onNavigateUp: () -> Unit) { // メイン画面に戻るためのコールバック
    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("設定") }, navigationIcon = {
                    IconButton(onClick = onNavigateUp) {
                        Icon(
                            imageVector = Icons.AutoMirrored.Filled.ArrowBack, contentDescription = "戻る"
                        )
                    }
                }, colors = TopAppBarDefaults.topAppBarColors( // メイン画面と色を合わせる例
                    containerColor = Color(0xFF1976D2),
                    titleContentColor = Color.White,
                    navigationIconContentColor = Color.White
                )
            )
        }) { innerPadding ->
        Column(
            modifier = Modifier
                .padding(innerPadding)
                .fillMaxSize() // コンテンツが画面全体に広がるように
                .padding(16.dp) // 内側のコンテンツにさらにパディング
        ) {
            Text("ここに設定項目が表示されます。")
            // 今後、ここに具体的な設定UI（Switch、TextFieldなど）を追加していきます。
            // 例:
            // var notificationsEnabled by remember { mutableStateOf(true) }
            // Row(verticalAlignment = Alignment.CenterVertically) {
            //     Text("通知を有効にする")
            //     Spacer(Modifier.weight(1f))
            //     Switch(
            //         checked = notificationsEnabled,
            //         onCheckedChange = { notificationsEnabled = it }
            //     )
            // }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    KotlinpracticeTheme {
        // Preview用にNavHostは不要なので、直接MyScreenを呼び出すか、
        // もしSettingsScreenをプレビューしたい場合はSettingsScreenを呼び出す
        MyScreen(onNavigateToSettings = {})
// SettingsScreen(onNavigateUp = {}) // SettingsScreenをプレビュー
    }
}