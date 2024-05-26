package inc.fabudi.foodies.ui.components

import android.graphics.BitmapFactory
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.asImageBitmap
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import inc.fabudi.foodies.R
import inc.fabudi.foodies.ui.theme.FoodiesTheme
import inc.fabudi.foodies.ui.theme.GrayBg
import inc.fabudi.foodies.ui.theme.HighEmphasis
import inc.fabudi.foodies.ui.theme.MediumEmphasis

@Composable
fun ItemCard(
    modifier: Modifier = Modifier,
    id: Int = 0,
    title: String = "Product title",
    weight: String = "500 g",
    price: String = "100 ₽",
    priceOld: String? = null,
    isDiscounted: Boolean = false,
    isSpicy: Boolean = false,
    hasNoMeat: Boolean = false,
    items: Int = 0,
    minusOnClick: (id: Int) -> Unit = {},
    plusOnClick: (id: Int) -> Unit = {},
    onClick: () -> Unit = {}
) {
    val context = LocalContext.current
    val image by remember{
        mutableStateOf(
            BitmapFactory.decodeStream(
                context.assets.open("product_photo.webp")
            )
            .asImageBitmap()
        )
    }
    Column(
        modifier = modifier
            .width(170.dp)
            .clip(RoundedCornerShape(8.dp))
            .background(GrayBg)
            .clickable { onClick() }
    ) {
        Box(modifier = Modifier.wrapContentSize()) {
            Image(
                bitmap = image,
                contentDescription = "Product photo",
                modifier = Modifier.size(170.dp)
            )
            Row(modifier = Modifier.padding(8.dp), horizontalArrangement = Arrangement.spacedBy(8.dp)) {
                if (isDiscounted) Icon(
                    painter = painterResource(id = R.drawable.discount),
                    contentDescription = "Discount icon",
                    tint = Color.Unspecified,
                    modifier = Modifier
                        .size(24.dp)
                )
                if (isSpicy) Icon(
                    painter = painterResource(id = R.drawable.spicy),
                    contentDescription = "Spicy icon",
                    tint = Color.Unspecified,
                    modifier = Modifier
                        .size(24.dp)
                )
                if (hasNoMeat) Icon(
                    painter = painterResource(id = R.drawable.no_meat),
                    contentDescription = "No meat icon",
                    tint = Color.Unspecified,
                    modifier = Modifier
                        .size(24.dp)
                )
            }
        }
        Column(modifier = Modifier.padding(12.dp)) {
            Text(
                text = title,
                modifier = Modifier.padding(bottom = 4.dp),
                style = MaterialTheme.typography.bodySmall,
                color = HighEmphasis
            )
            Text(
                text = weight,
                modifier = Modifier.padding(bottom = 12.dp),
                style = MaterialTheme.typography.bodySmall,
                color = MediumEmphasis
            )
            if (items>0) CounterLight(
                modifier = Modifier.fillMaxWidth().height(40.dp),
                items = items,
                minusOnClick = { minusOnClick(id) },
                plusOnClick = { plusOnClick(id) }
            )
            else AddToCartButton(modifier = Modifier.fillMaxWidth().height(40.dp),
                price = price,
                priceOld = priceOld,
                onClick = { plusOnClick(id) }
                )
        }
    }
}

@Preview
@Composable
private fun ItemCardPreview() {
    FoodiesTheme {
        Box(modifier = Modifier.background(Color.White)) {
            ItemCard()
        }
    }
}