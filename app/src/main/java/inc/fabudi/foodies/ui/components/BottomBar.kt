package inc.fabudi.foodies.ui.components

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.expandVertically
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.shrinkVertically
import androidx.compose.animation.slideInVertically
import androidx.compose.animation.slideOutVertically
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import inc.fabudi.foodies.R
import inc.fabudi.foodies.Utils.toPrice
import inc.fabudi.foodies.data.CartState

@Composable
fun BottomBar(
    modifier: Modifier = Modifier,
    cartState: CartState = CartState.Empty,
    onClick: () -> Unit = {}
) {
    val slidePixels = with(LocalDensity.current) { -40.dp.roundToPx() }
    AnimatedVisibility(
        visible = cartState is CartState.Filled,
        enter = slideInVertically { slidePixels }
                + expandVertically(expandFrom = Alignment.Top)
                + fadeIn(initialAlpha = 0.3f),
        exit = slideOutVertically() + shrinkVertically() + fadeOut()
    ) {
        Row(
            modifier = modifier
                .shadow(4.dp)
                .background(MaterialTheme.colorScheme.background),
            verticalAlignment = Alignment.CenterVertically
        ) {
            PrimaryButton(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(start = 16.dp, top = 12.dp, end = 16.dp, bottom = 12.dp),
                icon = {
                    Icon(
                        modifier = Modifier.size(20.dp),
                        painter = painterResource(id = R.drawable.cart),
                        contentDescription = "",
                        tint = MaterialTheme.colorScheme.onPrimary
                    )
                },
                text = if (cartState is CartState.Filled)
                    cartState.totalPrice.toPrice()
                       else "",
                onClick = onClick
            )
        }
    }
}

@Preview
@Composable
private fun BottomBarPreview() {
    BottomBar()
}