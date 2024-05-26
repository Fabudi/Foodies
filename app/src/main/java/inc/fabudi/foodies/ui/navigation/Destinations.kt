package inc.fabudi.foodies.ui.navigation

sealed class Destination(val route: String) {
    data object Home : Destination("home")
    data object Search : Destination("search")
    data object Details : Destination("details")
    data object Cart : Destination("cart")
}