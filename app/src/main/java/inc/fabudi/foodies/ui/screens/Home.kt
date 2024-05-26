package inc.fabudi.foodies.ui.screens

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.rememberModalBottomSheetState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.navigation.NavController
import inc.fabudi.foodies.Utils.toPrice
import inc.fabudi.foodies.data.CartState
import inc.fabudi.foodies.data.Product
import inc.fabudi.foodies.network.ApiState
import inc.fabudi.foodies.ui.components.BottomSheetWithFilterDialog
import inc.fabudi.foodies.ui.components.ProductsGrid
import inc.fabudi.foodies.ui.components.TopBar
import inc.fabudi.foodies.ui.components.bottombar.BottomBar
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
    var bottomSheetIsOpened by rememberSaveable { mutableStateOf(false) }
    val bottomSheetState = rememberModalBottomSheetState()

    LaunchedEffect(Unit) {
        viewmodel.fetchProducts()
        viewmodel.fetchCategories()
        viewmodel.fetchTags()
    }

    Scaffold(modifier = Modifier,
        topBar = {
            TopBar(
                apiState = categoriesState.value,
                categoryOnClick = { id ->
                    viewmodel.selectedCategory.intValue = id
                    viewmodel.filter()
                },
                filterCounter = viewmodel.prevSelectedTags.value.size,
                filterOnClick = {
                    bottomSheetIsOpened = true
                    scope.launch { bottomSheetState.show() }
                }
            )
        },
        bottomBar = {
            BottomBar(
                text = viewmodel.getTotalPrice().toPrice(),
                visible = cartState.value is CartState.Filled,
                qty = if (cartState.value is CartState.Filled)
                        (cartState.value as CartState.Filled).products.sumOf { it.second }
                      else 0,
                onClick = { navController.navigate("Cart") }
            )
        }
    ) {
        when (productsState.value) {
            is ApiState.Success -> {
                val products = (productsState.value as ApiState.Success).data as List<Product>
                ProductsGrid(
                    modifier = Modifier.padding(it),
                    products = products,
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
                message = (productsState.value as ApiState.Error).message
            )

            else -> ErrorMessageDisplay(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(it),
                message = "Replace with shimmers"
            )
        }
    }
    BottomSheetWithFilterDialog(sheetState = bottomSheetState,
        isOpened = bottomSheetIsOpened,
        tagsState = tagsState.value,
        prevSelectedTags = viewmodel.prevSelectedTags,
        selectedTags = viewmodel.selectedTags,
        onDismissRequest = {
            bottomSheetIsOpened = false
            viewmodel.resetFilter()
        },
        onCheckedChange = { tag -> viewmodel.onTagChecked(tag) },
        onClick = {
            bottomSheetIsOpened = false
            scope.launch { bottomSheetState.hide() }
            viewmodel.filter()
        }
    )
}