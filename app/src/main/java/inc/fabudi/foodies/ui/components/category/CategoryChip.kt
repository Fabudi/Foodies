package inc.fabudi.foodies.ui.components.category

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.FilledTonalButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import inc.fabudi.foodies.ui.theme.HighEmphasis

@Composable
fun CategoryChip(
    modifier: Modifier = Modifier,
    text: String = "Category",
    isSelected: Boolean = true,
    onClick: () -> Unit = {}
) {
    FilledTonalButton(
        modifier = modifier,
        onClick = onClick,
        shape = RoundedCornerShape(8.dp),
        contentPadding = PaddingValues(16.dp, 12.dp, 16.dp, 12.dp),
        colors = ButtonDefaults.elevatedButtonColors(
            containerColor = if (isSelected) MaterialTheme.colorScheme.primary else Color.Transparent,
            contentColor = if (isSelected) MaterialTheme.colorScheme.onPrimary else HighEmphasis
        )
    ) {
        Text(
            text = text,
            style = MaterialTheme.typography.labelMedium,
            color = if (isSelected) MaterialTheme.colorScheme.onPrimary else HighEmphasis
        )
    }
}

@Preview
@Composable
private fun CategoryChipPreview() {
    CategoryChip()
}