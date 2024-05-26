package inc.fabudi.foodies.ui.components.button

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.FilledTonalButton
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import inc.fabudi.foodies.R
import inc.fabudi.foodies.ui.components.FilterCounter
import inc.fabudi.foodies.ui.theme.FoodiesTheme

@Composable
fun PrimaryButton(
    modifier: Modifier = Modifier,
    icon: @Composable () -> Unit = {},
    text: String = "",
    qty: Int = 0,
    onClick: () -> Unit = {}
) {
    FilledTonalButton(
        modifier = modifier,
        onClick = onClick,
        shape = RoundedCornerShape(8.dp),
        contentPadding = PaddingValues(16.dp, 12.dp, 16.dp, 12.dp),
        elevation = ButtonDefaults.elevatedButtonElevation(4.dp, 4.dp, 4.dp, 4.dp, 4.dp),
        colors = ButtonDefaults.elevatedButtonColors(
            containerColor = MaterialTheme.colorScheme.primary,
            contentColor = MaterialTheme.colorScheme.onPrimary
        )
    ) {
        Box {
            icon()
            if(qty!=0)
                FilterCounter(
                    value = qty,
                    modifier = Modifier.offset(8.dp, (-8).dp),
                    backgroundColor = MaterialTheme.colorScheme.background,
                    textColor = MaterialTheme.colorScheme.primary
                )
        }
        Text(
            modifier = Modifier.padding(start = 8.dp),
            text = text,
            style = MaterialTheme.typography.labelMedium,
            color = MaterialTheme.colorScheme.onPrimary
        )
    }
}

@Preview
@Composable
private fun PrimaryButtonPreview() {
    FoodiesTheme{
        PrimaryButton(
            modifier = Modifier.fillMaxWidth(), icon = {
                Icon(
                    modifier = Modifier.size(20.dp),
                    painter = painterResource(id = R.drawable.cart),
                    contentDescription = "",
                    tint = MaterialTheme.colorScheme.onPrimary
                )
            }, text = "2 160 ₽"
        )
    }
}