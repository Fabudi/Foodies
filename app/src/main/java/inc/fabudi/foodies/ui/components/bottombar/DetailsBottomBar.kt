package inc.fabudi.foodies.ui.components.bottombar

import androidx.compose.animation.animateContentSize
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import inc.fabudi.foodies.R
import inc.fabudi.foodies.Utils.toPrice
import inc.fabudi.foodies.data.Product
import inc.fabudi.foodies.ui.components.CounterLight
import inc.fabudi.foodies.ui.components.button.PrimaryButton
import inc.fabudi.foodies.ui.theme.FoodiesTheme

@Composable
fun DetailsBottomBar(
    modifier: Modifier = Modifier,
    item: Pair<Product, Int>,
    totalQty: Int = 0,
    isProductInCart: Boolean = true,
    addToCartOnClick: ()-> Unit = {},
    inCartOnClick: ()-> Unit = {},
    minusOnClick: ()-> Unit = {},
    plusOnClick : ()-> Unit = {}
) {
    Row(
        modifier = modifier
            .fillMaxWidth()
            .shadow(4.dp)
            .background(MaterialTheme.colorScheme.background)
    ) {
        Row(modifier = Modifier,
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.Center) {
            CounterLight(
                modifier = Modifier
                    .animateContentSize()
                    .then(
                        if (isProductInCart)
                            Modifier
                                .padding(start = 16.dp, top = 12.dp, end = 16.dp, bottom = 36.dp)
                                .weight(1f)
                        else Modifier.size(0.dp)
                    ),
                items = item.second,
                minusOnClick = minusOnClick,
                plusOnClick = plusOnClick,
            )
            PrimaryButton(
                modifier = Modifier
                    .fillMaxWidth()
                    .animateContentSize()
                    .weight(1f)
                    .padding(start = 16.dp, top = 12.dp, end = 16.dp, bottom = 36.dp),
                icon = {
                    if (isProductInCart) Icon(
                        modifier = Modifier.size(20.dp),
                        painter = painterResource(id = R.drawable.cart),
                        contentDescription = "",
                        tint = MaterialTheme.colorScheme.onPrimary
                    )
                },
                qty = if(isProductInCart)totalQty else 0,
                text = if (isProductInCart) (item.first.price_current * item.second).toPrice() else "В корзину за ${item.first.price_current.toPrice()}",
                onClick = { if (isProductInCart) inCartOnClick() else addToCartOnClick() }
            )
        }
    }
}

@Preview
@Composable
private fun DetailsBottomBarPreview() {
    FoodiesTheme {
        DetailsBottomBar(
            item = Pair(Product(
                carbohydrates_per_100_grams = 10.11,
                category_id = 1894,
                description = "neglegentur",
                energy_per_100_grams = 12.13,
                fats_per_100_grams = 14.15,
                id = 1244,
                image = "graece",
                measure = 9446,
                measure_unit = "novum",
                name = "Christopher Spencer",
                price_current = 4114,
                price_old = 8299,
                proteins_per_100_grams = 16.17,
                tag_ids = listOf()
            ), 2)
        )
    }
}