package inc.fabudi.foodies.ui.screens

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Checkbox
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import inc.fabudi.foodies.data.Tag
import inc.fabudi.foodies.network.ApiState
import inc.fabudi.foodies.ui.components.button.PrimaryButton
import inc.fabudi.foodies.ui.theme.FoodiesTheme
import inc.fabudi.foodies.ui.theme.Outline

@Composable
fun FilterDialog(
    modifier: Modifier = Modifier,
    tagsState: ApiState = ApiState.Loading,
    selectedTags: MutableState<List<Tag>> = mutableStateOf(emptyList()),
    onCheckedChange: (tag: Tag) -> Unit = {},
    onClick: () -> Unit = {}
) {
    Column(
        modifier = modifier
            .fillMaxWidth()
            .padding(top = 32.dp, start = 24.dp, end = 24.dp, bottom = 32.dp)
    ) {
        Text(
            text = "Подобрать блюда",
            style = MaterialTheme.typography.headlineSmall,
            modifier = Modifier.padding(bottom = 16.dp)
        )
        if (tagsState is ApiState.Success) {
            val tags = tagsState.data as List<Tag>
            for (tag in tags) {
                FilterSelector(
                    modifier = Modifier.fillMaxWidth(),
                    text = tag.name,
                    isChecked = selectedTags.value.contains(tag),
                    onCheckedChange = { onCheckedChange(tag) }
                )
                if (tags.last() != tag) HorizontalDivider(thickness = 1.dp, color = Outline)
            }
            PrimaryButton(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 16.dp),
                text = "Готово",
                onClick = onClick
            )
        }
    }
}

@Composable
fun FilterSelector(
    modifier: Modifier = Modifier,
    text: String = "Filter1",
    isChecked: Boolean = false,
    onCheckedChange: (Boolean) -> Unit = {}
) {
    Row(
        modifier = modifier,
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        Text(
            modifier = Modifier.padding(top = 12.dp, bottom = 12.dp), text = text, style = MaterialTheme.typography.bodyLarge
        )
        Checkbox(
            checked = isChecked, onCheckedChange = onCheckedChange
        )
    }
}

@Preview
@Composable
private fun FilterSelectorPreview() {
    FilterSelector(
        modifier = Modifier.fillMaxWidth(),
    )
}

@Preview
@Composable
private fun FilterDialogPreview() {
    FoodiesTheme {
        FilterDialog(
            tagsState = ApiState.Success(
                listOf(
                    Tag(1, "Filter1"),
                    Tag(2, "Filter2"),
                    Tag(3, "Filter3"),
                )
            )
        )
    }
}