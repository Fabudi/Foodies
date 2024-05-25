package inc.fabudi.foodies.ui.screens

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import inc.fabudi.foodies.data.Product
import inc.fabudi.foodies.network.ApiState
import inc.fabudi.foodies.ui.components.BottomBar
import inc.fabudi.foodies.ui.components.ProductsGrid
import inc.fabudi.foodies.ui.components.TopBar
import inc.fabudi.foodies.viewmodel.MainViewModel

@Composable
fun Home(modifier: Modifier = Modifier, viewmodel: MainViewModel) {
    val productsState = viewmodel.sortedProductsState.collectAsState()
    val categoriesState = viewmodel.categoriesState.collectAsState()
    val tagsState = viewmodel.tagsState.collectAsState()
    val cartState = viewmodel.cartState.collectAsState()

    LaunchedEffect(Unit) {
        viewmodel.fetchProducts()
        viewmodel.fetchCategories()
        viewmodel.fetchTags()
    }

    Scaffold(modifier = modifier, topBar = {
        TopBar(modifier = Modifier
            .fillMaxWidth()
            .wrapContentHeight(),
            apiState = categoriesState.value,
            categoryOnClick = { id ->
                viewmodel.selectedCategory.intValue = id
                viewmodel.selectCategory()
            })
    }, bottomBar = {
        BottomBar(
            cartState = cartState.value
        )
    }) {
        when (productsState.value) {
            is ApiState.Success -> {
                val products = (productsState.value as ApiState.Success).data as List<Product>
                ProductsGrid(
                    modifier = Modifier.padding(it),
                    products = products,
                    cartState = cartState.value,
                    minusOnClick = { id -> viewmodel.removeItem(id) },
                    plusOnClick = { id -> viewmodel.addItem(id) },
                    onClick = {}
                )
            }
            else -> {
                Box(
                    modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center
                ) {
                    Text(
                        text = "Replace with shimmers", style = MaterialTheme.typography.labelLarge
                    )
                }
            }
        }
    }
}