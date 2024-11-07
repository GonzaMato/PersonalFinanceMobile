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
import androidx.navigation.navArgument
import com.example.diseomobile.pages.dolarPrices.DolarPricePage
import com.example.diseomobile.pages.graphPage.GraphPage
import com.example.diseomobile.pages.homePage.HomePage
import com.example.diseomobile.pages.movementPage.MovementPage
import com.example.diseomobile.pages.newTransaction.AddFunds

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
            HomePage (
                navigateToNewTransaction = { navController.navigate(WiseRipOffScreens.NewTransaction.name) },
                navigateToMovement = { movementId -> navController.navigate("${WiseRipOffScreens.Movement.name}/$movementId") }
            )
        }
        composable(WiseRipOffScreens.NewTransaction.name) {
            AddFunds { navController.navigate(WiseRipOffScreens.Home.name) }
        }
        composable(WiseRipOffScreens.Currencies.name) {
            DolarPricePage()
        }
        composable(WiseRipOffScreens.Graphs.name) {
            GraphPage(
                navigateToMovement = { movementId -> navController.navigate("${WiseRipOffScreens.Movement.name}/$movementId") }
            )
        }
        composable(
            route = WiseRipOffScreens.Movement.name + "/{MovementId}",
            arguments = listOf(navArgument("MovementId") { defaultValue = 0 })
        ) { backStackEntry ->
            val movementId = backStackEntry.arguments?.getInt("MovementId") ?: 0
            MovementPage(navController = { navController.popBackStack() }, movementId)
        }

    }
}


