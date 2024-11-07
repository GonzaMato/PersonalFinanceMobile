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
import com.example.diseomobile.pages.dolarPrices.DolarPricePage
import com.example.diseomobile.pages.newTransaction.AddFunds
import com.example.diseomobile.pages.graphPage.GraphPage
import com.example.diseomobile.pages.homePage.HomePage
import com.example.diseomobile.pages.movementPage.MovementPage

@Composable
fun NavHostComposable(navController: NavHostController, innerPadding: PaddingValues) {
    NavHost(
        navController = navController,
        startDestination = WiseRipOffScreens.Home.name,
        modifier = Modifier
            .fillMaxWidth()
            .padding(innerPadding)
            .padding(horizontal = 1.dp)
    ) {
        composable(WiseRipOffScreens.Home.name) {
            HomePage { navController.navigate(WiseRipOffScreens.NewTransaction.name) }
        }
        composable(WiseRipOffScreens.NewTransaction.name) {
            AddFunds { navController.navigate(WiseRipOffScreens.Home.name) }
        }
        composable(WiseRipOffScreens.Currencies.name) {
            DolarPricePage()
        }
        composable(WiseRipOffScreens.Graphs.name) {
            GraphPage()
        }

    }
}
