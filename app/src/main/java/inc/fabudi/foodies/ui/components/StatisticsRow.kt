package inc.fabudi.foodies.ui.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import inc.fabudi.foodies.ui.theme.MediumEmphasis

@Composable
fun StatisticsRow(modifier: Modifier = Modifier, type: String = "", value: String = "") {
    Row(
        modifier = modifier.fillMaxWidth().height(50.dp),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Text(text = type, style = MaterialTheme.typography.bodyLarge, color = MediumEmphasis)
        Text(text = value, style = MaterialTheme.typography.bodyLarge)
    }
}

@Preview
@Composable
private fun StatisticsRowPreview() {
    StatisticsRow()
}