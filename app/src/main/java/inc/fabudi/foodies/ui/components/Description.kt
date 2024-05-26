package inc.fabudi.foodies.ui.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import inc.fabudi.foodies.R
import inc.fabudi.foodies.ui.theme.MediumEmphasis

@Composable
fun Description(
    modifier: Modifier = Modifier,
    name: String = "name",
    ingredients: String = "ingredients",
    isDiscounted: Boolean = false,
    isSpicy: Boolean = false,
    hasNoMeat: Boolean = false
) {
    Column(modifier = modifier) {
        if (isDiscounted || isSpicy || hasNoMeat)
            Row(modifier = Modifier.padding(top = 8.dp, bottom = 8.dp), horizontalArrangement = Arrangement.spacedBy(8.dp)) {
                if (isDiscounted) Icon(
                    painter = painterResource(id = R.drawable.discount),
                    contentDescription = "Discount icon",
                    tint = Color.Unspecified,
                    modifier = Modifier
                        .size(36.dp)
                )
                if (isSpicy) Icon(
                    painter = painterResource(id = R.drawable.spicy),
                    contentDescription = "Spicy icon",
                    tint = Color.Unspecified,
                    modifier = Modifier
                        .size(36.dp)
                )
                if (hasNoMeat) Icon(
                    painter = painterResource(id = R.drawable.no_meat),
                    contentDescription = "No meat icon",
                    tint = Color.Unspecified,
                    modifier = Modifier
                        .size(36.dp)
                )
            }
        Text(
            text = name,
            style = MaterialTheme.typography.headlineLarge,
            modifier = Modifier.padding(bottom = 8.dp)
        )
        Text(text = ingredients, style = MaterialTheme.typography.bodyLarge, color = MediumEmphasis)
    }
}

@Preview
@Composable
private fun DescriptionPreview() {
    Description()
}