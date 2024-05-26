package inc.fabudi.foodies.ui.components.button

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.FilledTonalButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import inc.fabudi.foodies.ui.theme.FoodiesTheme
import inc.fabudi.foodies.ui.theme.OldPrice

@Composable
fun AddToCartButton(
    modifier: Modifier = Modifier, price: String, priceOld: String? = null, onClick: () -> Unit = {}
) {
    FilledTonalButton(
        modifier = modifier,
        onClick = onClick,
        shape = RoundedCornerShape(8.dp),
        contentPadding = PaddingValues(0.dp),
        elevation = ButtonDefaults.elevatedButtonElevation(4.dp, 4.dp, 4.dp, 4.dp, 4.dp),
        colors = ButtonDefaults.elevatedButtonColors(
            containerColor = MaterialTheme.colorScheme.background
        )
    ) {
        Row(modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.Center) {
            Text(
                text = price,
                maxLines = 1,
                style = MaterialTheme.typography.labelMedium,
            )
            if (priceOld != null)
                Text(
                    modifier = Modifier.padding(start = 8.dp),
                    text = priceOld,
                    maxLines = 1,
                    style = MaterialTheme.typography.labelMedium,
                    color = OldPrice,
                    textDecoration = TextDecoration.LineThrough
                )
        }
    }
}

@Preview
@Composable
private fun AddToCartButtonPreview() {
    FoodiesTheme {
        Box(
            modifier = Modifier
                .background(Color.White)
                .padding(8.dp)
        ) {
            AddToCartButton(price = "480 ₽")
        }
    }
}

@Preview
@Composable
private fun AddToCartButtonOldPricePreview() {
    FoodiesTheme {
        Box(
            modifier = Modifier
                .background(Color.White)
                .padding(8.dp)
                .width(100.dp)
        ) {
            AddToCartButton(price = "1480 ₽", priceOld = "123900 ₽")
        }
    }
}