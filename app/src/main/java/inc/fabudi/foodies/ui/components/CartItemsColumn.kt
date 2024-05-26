package inc.fabudi.foodies.ui.components

import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.HorizontalDivider
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import inc.fabudi.foodies.data.Product
import inc.fabudi.foodies.ui.components.itemcard.CartItemCard
import inc.fabudi.foodies.ui.theme.Outline

@Composable
fun CartItemsColumn(
    modifier: Modifier = Modifier,
    items: List<Pair<Product, Int>>,
    minusOnClick: (Int) -> Unit = { },
    plusOnClick: (Int) -> Unit = { }
) {
    LazyColumn(modifier = modifier) {
        items(items) { item ->
            CartItemCard(
                modifier = Modifier.animateItem(),
                item = item,
                minusOnClick = minusOnClick,
                plusOnClick = plusOnClick
            )
            HorizontalDivider(color = Outline, thickness = 1.dp)
        }
    }
}