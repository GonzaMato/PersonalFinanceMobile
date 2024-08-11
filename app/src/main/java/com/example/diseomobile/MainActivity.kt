package com.example.diseomobile

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.example.diseomobile.Button.ButtonMain
import com.example.diseomobile.ui.theme.DiseñoMobileTheme
import com.example.diseomobile.ui.theme.PrimaryColor

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            DiseñoMobileTheme {
                Surface(modifier = Modifier.fillMaxSize()) {
                    Homepage()
                }
            }
        }
    }
}

@Composable
fun Homepage() {
    Column(
        modifier = Modifier.fillMaxSize()
    ) {
        Text(text = "Welcome toe app", style = MaterialTheme.typography.bodyMedium)
        ButtonMain(color = PrimaryColor, buttonText = "Ahhh")
    }
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    DiseñoMobileTheme {
        Homepage()
    }
}