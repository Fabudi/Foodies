package inc.fabudi.foodies.ui.components.button

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import inc.fabudi.foodies.R
import inc.fabudi.foodies.ui.theme.Dark

@Composable
fun CircleIconButton(
    modifier: Modifier = Modifier,
    icon: @Composable () -> Unit = {
        Icon(
            modifier = Modifier.size(24.dp),
            painter = painterResource(id = R.drawable.back),
            contentDescription = "Back button",
            tint = MaterialTheme.colorScheme.primary
        )
    },
    onClick: () -> Unit = {}
) {
    IconButton(
        onClick = onClick, modifier = modifier
            .padding(16.dp)
            .shadow(
                elevation = 4.dp,
                shape = CircleShape,
                spotColor = Dark,
                ambientColor = Dark
            )
            .background(MaterialTheme.colorScheme.background)
            .wrapContentSize()
    ) {
        icon()
    }
}

@Preview
@Composable
private fun CircleIconButtonPreview() {
    CircleIconButton()
}