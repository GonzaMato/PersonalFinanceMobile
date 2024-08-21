package com.example.diseomobile.navigation

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.example.diseomobile.Homepage
import com.example.diseomobile.pages.GraphPage

@Composable
fun NavHostComposable(navController: NavHostController, innerPadding: PaddingValues) {
    NavHost(
        navController = navController,
        startDestination = WiseRipOffScreens.Home.name,
        modifier = Modifier.fillMaxWidth().padding(innerPadding).padding(horizontal = 24.dp)
    ) {
        composable(WiseRipOffScreens.Home.name) {
            Homepage()
        }

        composable(WiseRipOffScreens.Graphs.name) {
            GraphPage()
        }
    }
}
