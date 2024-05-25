package inc.fabudi.foodies.ui.components

import android.graphics.BitmapFactory
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.asImageBitmap
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import inc.fabudi.foodies.Utils.toPrice
import inc.fabudi.foodies.data.Product
import inc.fabudi.foodies.ui.theme.FoodiesTheme
import inc.fabudi.foodies.ui.theme.MediumEmphasis

@Composable
fun CartItemCard(
    modifier: Modifier = Modifier,
    item: Pair<Product, Int>,
    minusOnClick: (id: Int) -> Unit = {},
    plusOnClick: (id: Int) -> Unit = {},
) {
    val context = LocalContext.current
    val image by remember {
        mutableStateOf(
            BitmapFactory.decodeStream(
                context.assets.open("product_photo.png")
            ).asImageBitmap()
        )
    }
    Row(
        modifier = modifier
            .background(MaterialTheme.colorScheme.background)
            .height(130.dp)
            .padding(16.dp),
        horizontalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        Image(
            bitmap = image,
            contentDescription = "Product photo",
            modifier = Modifier.size(96.dp)
        )
        Column(
            modifier = Modifier.fillMaxHeight(),
            verticalArrangement = Arrangement.SpaceBetween
        ) {
            Text(text = item.first.name, style = MaterialTheme.typography.bodySmall)
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(46.dp),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Counter(
                    modifier = Modifier.width(135.dp),
                    items = item.second.toString(),
                    minusOnClick = { minusOnClick(item.first.id) },
                    plusOnClick = { plusOnClick(item.first.id) }
                )
                Column(
                    modifier = Modifier.fillMaxHeight(),
                    verticalArrangement = Arrangement.Center,
                    horizontalAlignment = Alignment.End
                ) {
                    Text(
                        text = (item.first.price_current*item.second).toPrice(),
                        style = MaterialTheme.typography.bodyMedium
                    )
                    if (item.first.price_old != 0) {
                        Spacer(modifier = Modifier.weight(1f))
                        Text(
                            text = (item.first.price_old*item.second).toPrice(),
                            style = MaterialTheme.typography.bodySmall,
                            color = MediumEmphasis,
                            textDecoration = TextDecoration.LineThrough
                        )
                    }
                }
            }
        }
    }
}

@Preview
@Composable
private fun CartItemCardPreview() {
    FoodiesTheme {
        CartItemCard(
            modifier = Modifier.fillMaxWidth(),
            item = Pair(
                Product(
                    carbohydrates_per_100_grams = 24.25,
                    category_id = 1822,
                    description = "qualisque",
                    energy_per_100_grams = 26.27,
                    fats_per_100_grams = 28.29,
                    id = 7008,
                    image = "senserit",
                    measure = 9477,
                    measure_unit = "possit",
                    name = "Magdalena Ortiz",
                    price_current = 9377,
                    price_old = 2200,
                    proteins_per_100_grams = 30.31,
                    tag_ids = listOf()
                ), 3
            )
        )
    }
}