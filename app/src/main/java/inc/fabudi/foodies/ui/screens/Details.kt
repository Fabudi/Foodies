package inc.fabudi.foodies.ui.screens

import android.graphics.BitmapFactory
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.systemBarsPadding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.asImageBitmap
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import inc.fabudi.foodies.data.CartState
import inc.fabudi.foodies.ui.components.Description
import inc.fabudi.foodies.ui.components.Statistics
import inc.fabudi.foodies.ui.components.bottombar.DetailsBottomBar
import inc.fabudi.foodies.ui.components.button.CircleIconButton
import inc.fabudi.foodies.viewmodel.MainViewModel

@Composable
fun Details(viewmodel: MainViewModel, navController: NavController, productId: Int) {
    val context = LocalContext.current
    val cartState = viewmodel.cartState.collectAsState()
    val product by remember {
        mutableStateOf(viewmodel.getProduct(productId))
    }
    val image by remember {
        mutableStateOf(
            BitmapFactory.decodeStream(
                context.assets.open("product_photo.webp")
            ).asImageBitmap()
        )
    }
    Box(modifier = Modifier.fillMaxSize()) {
        Scaffold(
            modifier = Modifier,
            bottomBar = {
            val cartItem =
                if (cartState.value is CartState.Filled)
                        (cartState.value as CartState.Filled).products
                            .find{it.first.id == productId} ?: Pair(viewmodel.getProduct(productId), 0)
                else Pair(viewmodel.getProduct(productId), 0)
            DetailsBottomBar(
                modifier = Modifier,
                item = cartItem,
                totalQty = if (cartState.value is CartState.Filled)
                        (cartState.value as CartState.Filled).products.sumOf { it.second }
                           else 0,
                isProductInCart = viewmodel.getProductQty(productId) > 0,
                addToCartOnClick = { viewmodel.addItem(productId) },
                inCartOnClick = { navController.navigate("cart") },
                minusOnClick = { viewmodel.removeItem(productId) },
                plusOnClick = { viewmodel.addItem(productId) })
        }) {
            val scrollState = rememberScrollState()
            Column(
                modifier = Modifier
                    .padding(it)
                    .verticalScroll(scrollState)
            ) {
                Image(
                    bitmap = image,
                    contentDescription = "Product photo",
                    modifier = Modifier.fillMaxWidth()
                )
                Description(
                    modifier = Modifier.padding(
                        start = 16.dp, end = 16.dp, top = 24.dp, bottom = 24.dp
                    ),
                    name = product.name,
                    ingredients = product.description,
                    isDiscounted = product.price_old != 0,
                    isSpicy = product.tag_ids.contains(4), // "Острое" has id=4
                    hasNoMeat = product.tag_ids.contains(2), // "Вегетарианское блюдо" has id=2
                )
                Statistics(
                    units = product.measure_unit,
                    measure = product.measure,
                    calories = product.energy_per_100_grams,
                    proteins = product.proteins_per_100_grams,
                    fats = product.fats_per_100_grams,
                    carbohydrates = product.carbohydrates_per_100_grams
                )
            }
        }
        CircleIconButton(
            modifier = Modifier.systemBarsPadding(),
            onClick = { navController.popBackStack() })
    }
}

@Preview
@Composable
private fun DetailsPreview() {

}