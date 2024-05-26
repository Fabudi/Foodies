package inc.fabudi.foodies.ui.components

import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.ModalBottomSheet
import androidx.compose.material3.SheetState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.ui.Modifier
import inc.fabudi.foodies.data.Tag
import inc.fabudi.foodies.network.ApiState
import inc.fabudi.foodies.ui.screens.FilterDialog

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun BottomSheetWithFilterDialog(
    modifier: Modifier = Modifier,
    sheetState: SheetState,
    isOpened: Boolean,
    tagsState: ApiState,
    prevSelectedTags: MutableState<List<Tag>>,
    selectedTags: MutableState<List<Tag>>,
    onDismissRequest: () -> Unit,
    onCheckedChange: (Tag) -> Unit,
    onClick: () -> Unit
) {
    if (isOpened) {
        ModalBottomSheet(
            onDismissRequest = onDismissRequest,
            sheetState = sheetState,
            containerColor = MaterialTheme.colorScheme.background,
            windowInsets = WindowInsets(0,0,0,0)
        ) {
            FilterDialog(
                tagsState = tagsState,
                selectedTags = selectedTags,
                onCheckedChange = onCheckedChange,
                onClick = onClick
            )
        }
    }
}

