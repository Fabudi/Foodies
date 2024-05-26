package inc.fabudi.foodies.ui.components.bottombar

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.expandVertically
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.shrinkVertically
import androidx.compose.animation.slideInVertically
import androidx.compose.animation.slideOutVertically
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.navigationBars
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.windowInsetsPadding
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import inc.fabudi.foodies.R
import inc.fabudi.foodies.data.Product
import inc.fabudi.foodies.ui.components.button.PrimaryButton

@Composable
fun BottomBar(
    modifier: Modifier = Modifier,
    text: String = "",
    icon: @Composable () -> Unit = {
        Icon(
            modifier = Modifier.size(20.dp),
            painter = painterResource(id = R.drawable.cart),
            contentDescription = "Go to cart button",
            tint = MaterialTheme.colorScheme.onPrimary
        )
    },
    qty: Int = 0,
    visible: Boolean = true,
    onClick: () -> Unit = {}
) {
    val slidePixels = with(LocalDensity.current) { -40.dp.roundToPx() }
    AnimatedVisibility(
        visible = visible,
        enter = slideInVertically { slidePixels }
                + expandVertically(expandFrom = Alignment.Top)
                + fadeIn(initialAlpha = 0.3f),
        exit = slideOutVertically() + shrinkVertically() + fadeOut()
    ) {
        Row(
            modifier = modifier
                .shadow(4.dp)
                .background(MaterialTheme.colorScheme.background),
            verticalAlignment = Alignment.CenterVertically
        ) {
            PrimaryButton(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(start = 16.dp, top = 12.dp, end = 16.dp, bottom = 12.dp)
                    .windowInsetsPadding(WindowInsets.navigationBars),
                icon = icon,
                text = text,
                qty = qty,
                onClick = onClick
            )
        }
    }
}

@Preview
@Composable
private fun BottomBarPreview() {
    val items = setOf(
        Pair(
            Product(
                carbohydrates_per_100_grams = 40.41,
                category_id = 3963,
                description = "tation",
                energy_per_100_grams = 42.43,
                fats_per_100_grams = 44.45,
                id = 3436,
                image = "definiebas",
                measure = 7552,
                measure_unit = "nonumes",
                name = "Ramona Collier",
                price_current = 8172,
                price_old = 1894,
                proteins_per_100_grams = 46.47,
                tag_ids = listOf()
            ), 2
        ), Pair(
            Product(
                carbohydrates_per_100_grams = 0.0,
                category_id = 0,
                description = "",
                energy_per_100_grams = 0.0,
                fats_per_100_grams = 0.0,
                id = 0,
                image = "",
                measure = 0,
                measure_unit = "",
                name = "Lorem ipsum",
                price_current = 10000,
                price_old = 0,
                proteins_per_100_grams = 0.0,
                tag_ids = listOf()
            ), 3
        )
    )
    BottomBar()
}