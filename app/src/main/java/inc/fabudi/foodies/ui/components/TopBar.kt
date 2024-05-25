package inc.fabudi.foodies.ui.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.displayCutoutPadding
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.systemBarsPadding
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import inc.fabudi.foodies.R
import inc.fabudi.foodies.data.Category
import inc.fabudi.foodies.network.ApiState

@Composable
fun TopBar(
    modifier: Modifier = Modifier,
    apiState: ApiState = ApiState.Loading,
    filterOnClick: () -> Unit = {},
    searchOnClick: () -> Unit = {},
    categoryOnClick: (Int) -> Unit = {}
) {
    Column(
        modifier = modifier
            .systemBarsPadding()
            .displayCutoutPadding()
    ) {
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            IconButton(onClick = filterOnClick) {
                Icon(
                    painter = painterResource(id = R.drawable.filter),
                    contentDescription = "Filter button"
                )
            }
            Image(
                painterResource(id = R.drawable.logo),
                contentDescription = "App logo image",
                contentScale = ContentScale.Fit
            )
            IconButton(onClick = searchOnClick) {
                Icon(
                    painter = painterResource(id = R.drawable.search),
                    contentDescription = "Search button"
                )
            }
        }
        if (apiState is ApiState.Success) {
            CategorySelector(
                categories = apiState.data as List<Category>,
                onClick = categoryOnClick
            )
        }
    }
}

@Preview
@Composable
private fun TopBarPreview() {
    TopBar(modifier = Modifier
        .fillMaxWidth()
        .wrapContentHeight(),
        apiState = ApiState.Success(listOf(Category(id = 1569, name = "Elva Watkins")))
    )
}