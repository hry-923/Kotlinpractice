package com.example.kotlin_practice

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.kotlin_practice.ui.theme.KotlinpracticeTheme
// 他のスクリーンファイルをインポート (実際のパッケージ構造に合わせてください)
// import com.example.kotlin_practice.ui.screens.MyScreen
// import com.example.kotlin_practice.ui.screens.SettingsScreen
// import com.example.kotlin_practice.ui.screens.ProfileScreen

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            KotlinpracticeTheme {
                val navController = rememberNavController()
                NavHost(navController = navController, startDestination = "main") {
                    composable("main") {
                        // MyScreenを呼び出し (MyScreen.ktからインポート)
                        MyScreen(
                            onNavigateToSettings = { navController.navigate("settings") },
                            onNavigateToProfile = { navController.navigate("profile") }
                        )
                    }
                    composable("settings") {
                        // SettingsScreenを呼び出し (SettingsScreen.ktからインポート)
                        SettingsScreen(onNavigateUp = { navController.popBackStack() })
                    }
                    composable("profile") {
                        // ProfileScreenを呼び出し (ProfileScreen.ktからインポート)
                        ProfileScreen(onNavigateUp = { navController.popBackStack() })
                    }
                }
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun DefaultAppPreview() { // Preview関数名を変更して、アプリ全体を示すようにする
    KotlinpracticeTheme {
        // ナビゲーションを含むアプリ全体のプレビューは複雑になることがあるため、
        // ここでは特定の初期画面（例: MyScreen）をプレビューする形にするか、
        // NavHostを使ったプレビューのヘルパー関数を別途用意することを検討します。
        // 簡単のため、MyScreenを直接プレビューします。
        MyScreen(onNavigateToSettings = {}, onNavigateToProfile = {})
    }
}
