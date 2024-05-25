package inc.fabudi.foodies.ui.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.FilledIconButton
import androidx.compose.material3.FilledTonalButton
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import inc.fabudi.foodies.R
import inc.fabudi.foodies.ui.theme.FoodiesTheme
import inc.fabudi.foodies.ui.theme.GrayBg

@Composable
fun Counter(
    modifier: Modifier = Modifier,
    items: String = "9",
    minusOnClick: () -> Unit = {},
    plusOnClick: () -> Unit = {}
) {
    Row(
        modifier = modifier,
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.spacedBy(8.dp)
    ) {
        FilledIconButton(
            onClick = minusOnClick,
            colors = IconButtonDefaults.filledIconButtonColors(
                containerColor = GrayBg,
                contentColor = MaterialTheme.colorScheme.primary
            ),
            shape = RoundedCornerShape(8.dp)
        ) {
            Icon(
                painter = painterResource(id = R.drawable.minus),
                contentDescription = "Minus one item"
            )
        }
        Text(
            text = items,
            style = MaterialTheme.typography.labelMedium
        )
        FilledIconButton(
            onClick = plusOnClick,
            colors = IconButtonDefaults.filledIconButtonColors(
                containerColor = GrayBg,
                contentColor = MaterialTheme.colorScheme.primary
            ),
            shape = RoundedCornerShape(8.dp)
        ) {
            Icon(
                painter = painterResource(id = R.drawable.plus),
                contentDescription = "Minus one item"
            )
        }
    }
}

@Preview
@Composable
private fun CounterPreview() {
    FoodiesTheme {
        Box(
            modifier = Modifier
                .background(Color.Blue)
                .padding(8.dp)
        ) {
            Counter()
        }
    }
}

@Composable
fun CounterLight(
    modifier: Modifier = Modifier,
    items: Int = 9,
    minusOnClick: () -> Unit = {},
    plusOnClick: () -> Unit = {}
) {
    Row(
        modifier = modifier,
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        FilledTonalButton(
            onClick = minusOnClick,
            elevation = ButtonDefaults.elevatedButtonElevation(4.dp, 4.dp, 4.dp, 4.dp, 4.dp),
            colors = ButtonDefaults.elevatedButtonColors(
                containerColor = Color.White,
                contentColor = MaterialTheme.colorScheme.primary
            ),
            shape = RoundedCornerShape(8.dp),
            contentPadding = PaddingValues(8.dp),
            modifier = Modifier
                .height(40.dp)
                .aspectRatio(1f)
        ) {
            Icon(
                painter = painterResource(id = R.drawable.minus),
                contentDescription = "Minus one item"
            )
        }
        Text(
            text = items.toString(),
            style = MaterialTheme.typography.labelMedium
        )
        FilledTonalButton(
            onClick = plusOnClick,
            elevation = ButtonDefaults.elevatedButtonElevation(4.dp, 4.dp, 4.dp, 4.dp, 4.dp),
            colors = ButtonDefaults.elevatedButtonColors(
                containerColor = Color.White,
                contentColor = MaterialTheme.colorScheme.primary
            ),
            shape = RoundedCornerShape(8.dp),
            contentPadding = PaddingValues(0.dp, 0.dp, 0.dp, 0.dp),
            modifier = Modifier
                .height(40.dp)
                .aspectRatio(1f)
        ) {
            Icon(
                painter = painterResource(id = R.drawable.plus),
                contentDescription = "Minus one item"
            )
        }
    }
}

@Preview
@Composable
private fun CounterLightPreview() {
    FoodiesTheme {
        Box(
            modifier = Modifier
                .background(Color.White)
                .padding(8.dp)
        ) {
            CounterLight()
        }
    }
}