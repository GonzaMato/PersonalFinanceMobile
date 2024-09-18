package com.example.diseomobile

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.compose.rememberNavController
import com.example.diseomobile.navigation.BottomBar
import com.example.diseomobile.navigation.NavHostComposable
import com.example.diseomobile.ui.theme.DiseñoMobileTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class WiseRipOff : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            val navController = rememberNavController()
            DiseñoMobileTheme {
                Scaffold(
                    bottomBar = {
                        BottomBar {
                            navController.navigate(it)
                        }
                    }
                ) {
                    innerPadding ->
                    NavHostComposable(navController = navController, innerPadding = innerPadding)
                }
            }
        }
    }
}


@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    DiseñoMobileTheme {
    }
}