package inc.fabudi.foodies.ui.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.displayCutoutPadding
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import inc.fabudi.foodies.R
import inc.fabudi.foodies.data.Category
import inc.fabudi.foodies.network.ApiState
import inc.fabudi.foodies.ui.components.category.CategorySelector
import inc.fabudi.foodies.ui.theme.FoodiesTheme

@Composable
fun TopBar(
    modifier: Modifier = Modifier,
    apiState: ApiState = ApiState.Loading,
    filterCounter: Int = 2,
    filterOnClick: () -> Unit = {},
    searchOnClick: () -> Unit = {},
    categoryOnClick: (Int) -> Unit = {}
) {
    Box(modifier = Modifier.wrapContentSize()){
        Column(
            modifier = modifier
                .shadow(4.dp)
                .background(MaterialTheme.colorScheme.background)
                .padding(top = 16.dp, start = 8.dp, end = 8.dp, bottom = 8.dp)
                .displayCutoutPadding()
        ) {
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Box {
                    IconButton(onClick = filterOnClick, modifier = Modifier.size(44.dp)) {
                        Icon(
                            painter = painterResource(id = R.drawable.filter),
                            contentDescription = "Filter button"
                        )
                    }
                    if (filterCounter > 0)
                        FilterCounter(
                            value = filterCounter,
                            modifier = Modifier.align(Alignment.TopEnd)
                        )
                }
                Image(
                    painterResource(id = R.drawable.logo),
                    contentDescription = "App logo image",
                    contentScale = ContentScale.Fit,
                    modifier = Modifier.height(44.dp)
                )
                IconButton(onClick = searchOnClick, modifier = Modifier.size(44.dp)) {
                    Icon(
                        painter = painterResource(id = R.drawable.search),
                        contentDescription = "Search button"
                    )
                }
            }
            CategorySelector(
                state = apiState,
                onClick = categoryOnClick
            )

        }
    }
}

@Preview
@Composable
private fun TopBarPreview() {
    FoodiesTheme {
        TopBar(modifier = Modifier
            .fillMaxWidth()
            .wrapContentHeight(),
            apiState = ApiState.Success(listOf(Category(id = 1569, name = "Elva Watkins")))
        )
    }
}