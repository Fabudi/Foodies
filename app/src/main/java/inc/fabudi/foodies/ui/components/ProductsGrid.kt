package inc.fabudi.foodies.ui.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import inc.fabudi.foodies.Utils.findQty
import inc.fabudi.foodies.Utils.toPrice
import inc.fabudi.foodies.Utils.withMeasureUnit
import inc.fabudi.foodies.data.CartState
import inc.fabudi.foodies.data.Product

@Composable
fun ProductsGrid(
    modifier: Modifier = Modifier,
    products: List<Product> = emptyList(),
    cartState: CartState = CartState.Empty,
    minusOnClick: (id: Int) -> Unit = {},
    plusOnClick: (id: Int) -> Unit = {},
    onClick: (id: Int) -> Unit = {},
) {
    LazyVerticalGrid(
        modifier = modifier,
        columns = GridCells.Adaptive(170.dp),
        horizontalArrangement = Arrangement.spacedBy(8.dp, Alignment.CenterHorizontally),
        contentPadding = PaddingValues(start = 16.dp, end = 16.dp),
        verticalArrangement = Arrangement.spacedBy(8.dp)
    ) {
        items(products, key = { it.id }) { product ->
            val productsInCart =
                if (cartState is CartState.Filled) cartState.products.findQty(product.id) else 0
            ItemCard(
                modifier = Modifier.animateItem(),
                title = product.name,
                weight = product.measure.withMeasureUnit(product.measure_unit),
                price = product.price_current.toPrice(),
                priceOld = product.price_old.toPrice(),
                isDiscounted = product.price_old != 0,
                isSpicy = product.tag_ids.contains(4), // "Острое" has id 4
                hasNoMeat = product.tag_ids.contains(2), // "Вегетарианское блюдо" has id 2
                minusOnClick = { minusOnClick(product.id) },
                plusOnClick = { plusOnClick(product.id) },
                items = productsInCart,
                onClick = { onClick(product.id) }
            )
        }
    }
}

@Preview
@Composable
private fun ProductsGridPreview() {
    ProductsGrid()
}