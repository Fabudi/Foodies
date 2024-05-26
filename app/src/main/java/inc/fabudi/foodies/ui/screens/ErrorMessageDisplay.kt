package inc.fabudi.foodies.ui.screens

import androidx.compose.foundation.layout.Box
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview

@Composable
fun ErrorMessageDisplay(modifier: Modifier = Modifier, message: String = "") {
    Box(
        modifier = modifier, contentAlignment = Alignment.Center
    ) {
        Text(
            text = message,
            style = MaterialTheme.typography.labelLarge
        )
    }
}

@Preview
@Composable
private fun ErrorMessageDisplayPreview() {
    ErrorMessageDisplay()
}