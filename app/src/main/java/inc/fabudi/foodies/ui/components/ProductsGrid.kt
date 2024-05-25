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
import inc.fabudi.foodies.Utils.toMeasureUnit
import inc.fabudi.foodies.Utils.toPrice
import inc.fabudi.foodies.data.CartState
import inc.fabudi.foodies.data.Product

@Composable
fun ProductsGrid(
    modifier: Modifier = Modifier,
    products: List<Product> = emptyList(),
    cartState: CartState = CartState.Empty,
    minusOnClick: (id: Int) -> Int = {0},
    plusOnClick: (id: Int) -> Int = {0},
    onClick: () -> Unit = {},
) {
    LazyVerticalGrid(
        modifier = modifier,
        columns = GridCells.Adaptive(170.dp),
        horizontalArrangement = Arrangement.spacedBy(
            8.dp, Alignment.CenterHorizontally
        ),
        contentPadding = PaddingValues(start = 16.dp, end = 16.dp),
        verticalArrangement = Arrangement.spacedBy(8.dp)
    ) {
        items(products, key = { it.id }) { product ->
            ItemCard(
                modifier = Modifier.animateItem(),
                title = product.name,
                weight = product.measure.toMeasureUnit(product.measure_unit),
                price = product.price_current.toPrice()!!,
                priceOld = product.price_old.toPrice(),
                minusOnClick = { minusOnClick(product.id) },
                plusOnClick = { plusOnClick(product.id) },
                items = if (cartState is CartState.Filled) cartState.products.findQty(product.id)
                        else 0,
                onClick = onClick
            )
        }
    }
}

@Preview
@Composable
private fun ProductsGridPreview() {
    ProductsGrid()
}