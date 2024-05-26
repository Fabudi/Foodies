package inc.fabudi.foodies.ui.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import inc.fabudi.foodies.ui.theme.FoodiesTheme

@Composable
fun FilterCounter(
    modifier: Modifier = Modifier,
    value: Int = 0,
    backgroundColor: Color = MaterialTheme.colorScheme.primary,
    textColor: Color = MaterialTheme.colorScheme.onPrimary
) {
    Box(modifier = modifier
        .size(17.dp)
        .clip(CircleShape)
        .background(backgroundColor)){
            Text(
                text = value.toString(),
                modifier = Modifier.align(Alignment.Center),
                style = MaterialTheme.typography.labelSmall,
                color = textColor
            )
    }
}

@Preview
@Composable
private fun FilterCounterPreview() {
    FoodiesTheme {
        FilterCounter()
    }
}