package inc.fabudi.foodies.ui.screens

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import inc.fabudi.foodies.R
import inc.fabudi.foodies.Utils.toPrice
import inc.fabudi.foodies.data.CartState
import inc.fabudi.foodies.ui.components.CartItemsColumn
import inc.fabudi.foodies.ui.components.bottombar.BottomBar
import inc.fabudi.foodies.viewmodel.MainViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun Cart(viewmodel: MainViewModel, navController: NavController) {
    val cartState = viewmodel.cartState.collectAsState()

    Scaffold(
        modifier = Modifier,
        topBar = {
            TopAppBar(
                modifier = Modifier.shadow(4.dp),
                title = {
                    Text(
                        text = "Корзина", style = MaterialTheme.typography.headlineMedium
                    )
                },
                navigationIcon = {
                    IconButton(
                        onClick = {
                            if(!navController.popBackStack()) navController.navigate("Home")
                        }
                    ) {
                        Icon(
                            painter = painterResource(id = R.drawable.back),
                            contentDescription = "Back to Home screen button",
                            tint = MaterialTheme.colorScheme.primary,
                            modifier = Modifier.size(24.dp)
                        )
                    }
                }
            )
        },
        bottomBar = {
            BottomBar(
                text = "Заказать за ${viewmodel.getTotalPrice().toPrice()}",
                visible = cartState.value is CartState.Filled,
                icon = {},
                onClick = { navController.navigate("Cart") }
            )
        }
    ) {
        when (cartState.value) {
            is CartState.Filled -> CartItemsColumn(modifier = Modifier.padding(it),
                items = (cartState.value as CartState.Filled).products.toList()
                        .sortedBy { it.first.name },
                minusOnClick = { id -> viewmodel.removeItem(id) },
                plusOnClick = { id -> viewmodel.addItem(id) })

            else -> ErrorMessageDisplay(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(it),
                message = "Пусто, выберите блюда в каталоге :)"
            )
        }
    }
}
