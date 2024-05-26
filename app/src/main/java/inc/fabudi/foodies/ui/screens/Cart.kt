package inc.fabudi.foodies.ui.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import inc.fabudi.foodies.R
import inc.fabudi.foodies.data.CartState
import inc.fabudi.foodies.ui.components.CartItemCard
import inc.fabudi.foodies.ui.theme.Outline
import inc.fabudi.foodies.ui.components.bottombar.BottomBar
import inc.fabudi.foodies.viewmodel.MainViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun Cart(viewmodel: MainViewModel, navController: NavController) {
    val cartState = viewmodel.cartState.collectAsState()
    val items = (cartState.value as CartState.Filled).products.toList()

    Scaffold(modifier = Modifier, topBar = {
        TopAppBar(
            modifier = Modifier.shadow(4.dp),
            title = {
                Text(
                    text = "Корзина",
                    style = MaterialTheme.typography.headlineLarge
                )
            },
            navigationIcon = {
                IconButton(onClick = { navController.popBackStack() }) {
                    Icon(
                        painter = painterResource(id = R.drawable.back),
                        contentDescription = "Back to Home screen button",
                        tint = MaterialTheme.colorScheme.primary,
                        modifier = Modifier.size(24.dp)
                    )
                }
            }
        )
    }, bottomBar = {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .shadow(4.dp)
                .background(MaterialTheme.colorScheme.background),
            verticalAlignment = Alignment.CenterVertically
        ) {
            PrimaryButton(modifier = Modifier
                .fillMaxWidth()
                .padding(start = 16.dp, top = 12.dp, end = 16.dp, bottom = 12.dp),
                text = "Заказать за 2 160 ₽",
                onClick = { })
        }
    }) {
        LazyColumn(modifier = Modifier.padding(it)) {
            items(items) { item ->
                CartItemCard(item = item,
                    minusOnClick = { id -> viewmodel.removeItem(id) },
                    plusOnClick = { id -> viewmodel.addItem(id) })
                HorizontalDivider(color = Outline, thickness = 1.dp)
            }
        }
    }
}
