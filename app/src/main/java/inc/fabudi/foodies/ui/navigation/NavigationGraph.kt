package inc.fabudi.foodies.ui.navigation

import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import inc.fabudi.foodies.ui.screens.Cart
import inc.fabudi.foodies.ui.screens.Home
import inc.fabudi.foodies.viewmodel.MainViewModel

@Composable
fun NavigationGraph(navController: NavHostController, viewmodel: MainViewModel) {
    NavHost(
        navController,
        startDestination = Destination.Home.route,
        enterTransition = { fadeIn() },
        popEnterTransition = { fadeIn() },
        exitTransition = { fadeOut() },
        popExitTransition = { fadeOut() }
    ) {
        composable(route = Destination.Home.route) {
            Home(viewmodel, navController)
        }
        composable(route = Destination.Cart.route) {
            Cart(viewmodel, navController)
        }
    }
}