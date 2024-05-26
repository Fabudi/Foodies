package inc.fabudi.foodies.ui.screens

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.navigation.NavController
import inc.fabudi.foodies.network.ApiState
import inc.fabudi.foodies.ui.components.ProductsGrid
import inc.fabudi.foodies.ui.components.SearchBar
import inc.fabudi.foodies.viewmodel.MainViewModel

@Composable
fun Search(viewmodel: MainViewModel, navController: NavController) {
    val searchResultProductsState = viewmodel.searchResultProductsState.collectAsState()
    val cartState = viewmodel.cartState.collectAsState()

    Scaffold(
        topBar = {
            SearchBar(
                modifier = Modifier,
                searchQuery = viewmodel.searchQuery,
                onClick = { if(!navController.popBackStack()) navController.navigate("Home") },
                onValueChange = { viewmodel.searchQuery(it) }
            )
        }
    ) {
        when (searchResultProductsState.value) {
            is ApiState.Success -> {
                ProductsGrid(
                    modifier = Modifier.padding(it),
                    state = searchResultProductsState.value,
                    cartState = cartState.value,
                    minusOnClick = { id -> viewmodel.removeItem(id) },
                    plusOnClick = { id -> viewmodel.addItem(id) },
                    onClick = { id -> navController.navigate("Details/$id") }
                )
            }

            is ApiState.Error -> ErrorMessageDisplay(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(it),
                message = (searchResultProductsState.value as ApiState.Error).message
            )

            else -> ErrorMessageDisplay(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(it),
                message = "Replace with shimmers"
            )
        }
    }
}