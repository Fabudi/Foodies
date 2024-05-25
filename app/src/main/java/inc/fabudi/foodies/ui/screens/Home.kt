package inc.fabudi.foodies.ui.screens

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.ModalBottomSheet
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.rememberModalBottomSheetState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.navigation.NavController
import inc.fabudi.foodies.data.Product
import inc.fabudi.foodies.network.ApiState
import inc.fabudi.foodies.ui.components.BottomBar
import inc.fabudi.foodies.ui.components.ProductsGrid
import inc.fabudi.foodies.ui.components.TopBar
import inc.fabudi.foodies.ui.navigation.Destination
import inc.fabudi.foodies.viewmodel.MainViewModel
import kotlinx.coroutines.launch

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun Home(viewmodel: MainViewModel, navController: NavController) {
    val productsState = viewmodel.sortedProductsState.collectAsState()
    val categoriesState = viewmodel.categoriesState.collectAsState()
    val tagsState = viewmodel.tagsState.collectAsState()
    val cartState = viewmodel.cartState.collectAsState()


    val scope = rememberCoroutineScope()
    var openBottomSheet by rememberSaveable { mutableStateOf(false) }
    val bottomSheetState = rememberModalBottomSheetState(
        skipPartiallyExpanded = true
    )

    LaunchedEffect(Unit) {
        viewmodel.fetchProducts()
        viewmodel.fetchCategories()
        viewmodel.fetchTags()
    }

    Scaffold(modifier = Modifier, topBar = {
        TopBar(
            apiState = categoriesState.value,
            categoryOnClick = { id ->
                viewmodel.selectedCategory.intValue = id
                viewmodel.filter()
            },
            filterCounter = viewmodel.prevSelectedTags.value.size,
            filterOnClick = {
                openBottomSheet = true
                scope.launch { bottomSheetState.show() }
            })
    }, bottomBar = {
        BottomBar(
            cartState = cartState.value,
            onClick = {
                navController.navigate(Destination.Cart.route)
            }
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
            is ApiState.Error -> {
                Box(
                    modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center
                ) {
                    Text(
                        text = (productsState.value as ApiState.Error).message,
                        style = MaterialTheme.typography.labelLarge
                    )
                }
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
    if (openBottomSheet) {
        ModalBottomSheet(
            onDismissRequest = {
                openBottomSheet = false
                viewmodel.resetFilter()
            },
            sheetState = bottomSheetState,
            containerColor = MaterialTheme.colorScheme.background,
            windowInsets = WindowInsets(0,0,0,0)
        ) {
            FilterDialog(
                tagsState = tagsState.value,
                prevSelectedTags = viewmodel.prevSelectedTags,
                selectedTags = viewmodel.selectedTags,
                onCheckedChange = { tag -> viewmodel.onTagChecked(tag) },
                onClick = {
                    openBottomSheet = false
                    scope.launch { bottomSheetState.hide() }
                    viewmodel.filter()
                }
            )
        }
    }
}