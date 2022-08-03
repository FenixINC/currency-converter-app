package com.example.currency_converter_app.presentation.navigation.destination

import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.runtime.Composable
import androidx.navigation.NavBackStackEntry
import androidx.navigation.NavGraphBuilder
import com.example.currency_converter_app.presentation.ui.home.HomeScreen
import com.example.navigation.NavigationDestination
import com.google.accompanist.navigation.animation.composable

//private fun getComposableDestinations(): Map<NavigationDestination, @Composable (NavBackStackEntry) -> Unit> =
//    mapOf(
//        HomeDestination to { HomeScreen() },
//    )
//
//@OptIn(ExperimentalAnimationApi::class)
//fun NavGraphBuilder.addComposableDestinations() {
//    getComposableDestinations().forEach { entry ->
//        val destination = entry.key
//
//        composable(
//            destination.route(),
//            destination.arguments,
//            destination.deepLinks
//        ) { navBackStackEntry ->
//            entry.value(navBackStackEntry)
//        }
//    }
//}