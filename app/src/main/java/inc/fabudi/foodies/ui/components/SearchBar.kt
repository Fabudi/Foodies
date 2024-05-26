package inc.fabudi.foodies.ui.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.ProvideTextStyle
import androidx.compose.material3.SearchBarDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import inc.fabudi.foodies.R
import inc.fabudi.foodies.ui.theme.MediumEmphasis

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SearchBar(
    modifier: Modifier = Modifier,
    searchQuery: String = "",
    onClick: () -> Unit = {},
    onValueChange: (String) -> Unit = {}
) {
    Box(modifier = modifier.wrapContentSize()) {
        ProvideTextStyle(value = MaterialTheme.typography.bodyLarge) {
            androidx.compose.material3.SearchBar(query = searchQuery,
                onQueryChange = { onValueChange(it) },
                onSearch = { onValueChange(it) },
                active = false,
                onActiveChange = {},
                leadingIcon = {
                    IconButton(onClick = onClick) {
                        Icon(
                            painter = painterResource(id = R.drawable.back),
                            contentDescription = "Clear search field button",
                            tint = MaterialTheme.colorScheme.primary,
                            modifier = Modifier.size(24.dp)
                        )
                    }
                },
                trailingIcon = {
                    if (searchQuery.isNotEmpty())
                        IconButton(onClick = { onValueChange("") }) {
                            Icon(
                                painter = painterResource(id = R.drawable.cancel),
                                contentDescription = "Clear search field button",
                                tint = MediumEmphasis,
                                modifier = Modifier.size(24.dp)
                            )
                        }
                },
                shape = RectangleShape,
                modifier = Modifier
                    .fillMaxWidth()
                    .shadow(4.dp)
                    .background(MaterialTheme.colorScheme.background),
                colors = SearchBarDefaults.colors(
                    containerColor = MaterialTheme.colorScheme.background
                ),
                placeholder = {
                    Text(
                        text = "Найти блюдо",
                        style = MaterialTheme.typography.bodyLarge,
                        color = MediumEmphasis
                    )
                },
                content = {}
            )
        }
    }
}


@Preview
@Composable
fun SearchBarPreview() {
    SearchBar()
}