package inc.fabudi.foodies.ui.navigation

sealed class Destination(val route: String) {
    data object Home : Destination("home")
}