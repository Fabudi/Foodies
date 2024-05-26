package inc.fabudi.foodies.ui.components.category

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import inc.fabudi.foodies.data.Category
import inc.fabudi.foodies.network.ApiState
import kotlinx.coroutines.launch

@Composable
fun CategorySelector(
    modifier: Modifier = Modifier,
    onClick: (id: Int) -> Unit = {},
    state: ApiState = ApiState.Loading
) {
    var selected by remember {
        mutableStateOf("")
    }
    val lazyListState = rememberLazyListState()
    val coroutineScope = rememberCoroutineScope()
    LazyRow(
        modifier = modifier,
        state = lazyListState,
        horizontalArrangement = Arrangement.spacedBy(8.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        when(state){
            is ApiState.Success -> {
                val categories = state.data as List<Category>
                itemsIndexed(categories) { index, category ->
                    CategoryChip(
                        text = category.name,
                        isSelected = selected.contains(category.name),
                        onClick = {
                            coroutineScope.launch {
                                lazyListState.animateScrollToItem(index)
                            }
                            selected = if (selected == category.name) {
                                onClick(0)
                                ""
                            } else {
                                onClick(category.id)
                                category.name
                            }
                        }
                    )
                }
            }
            else -> {
                items(10){
                    CategoryChipShimmer()
                }
            }
        }

    }
}

@Preview
@Composable
private fun CategorySelectorPreview() {
    CategorySelector(
        state = ApiState.Loading
    )
}