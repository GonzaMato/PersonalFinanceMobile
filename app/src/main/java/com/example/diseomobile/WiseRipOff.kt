package com.example.diseomobile

import android.os.Bundle
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.tooling.preview.Preview
import androidx.fragment.app.FragmentActivity
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.compose.rememberNavController
import com.example.diseomobile.authentication.BiometricAuthManager
import com.example.diseomobile.authentication.MainActivityAuthentication
import com.example.diseomobile.navigation.BottomBar
import com.example.diseomobile.navigation.NavHostComposable
import com.example.diseomobile.pages.authenticationFail.AuthenticationFail
import com.example.diseomobile.ui.theme.DiseñoMobileTheme
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class WiseRipOff : FragmentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()

        setContent {
            val authViewModel : MainActivityAuthentication = hiltViewModel()
            val isAuthenticated by authViewModel.isAuthenticated.collectAsState()

            if (!isAuthenticated) {
                authViewModel.authenticate(this)
            }

            if (isAuthenticated) {
                loadMainContent()
            } else {
                AuthenticationFail()
                Toast.makeText(this, "Authentication Error", Toast.LENGTH_SHORT).show()
            }
        }
    }

    private fun loadMainContent() {
        setContent {
            val navController = rememberNavController()
            DiseñoMobileTheme {
                Scaffold(
                    bottomBar = {
                        BottomBar {
                            navController.navigate(it)
                        }
                    }
                ) { innerPadding ->
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