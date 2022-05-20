package org.singularux.messages.ui.component

import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.TopAppBarScrollBehavior
import androidx.compose.runtime.Composable

@ExperimentalMaterial3Api
@Composable
fun ThreadsTopBar(
    selectedItems: Int,
    onSearchClick: () -> Unit,
    onCloseClick: () -> Unit,
    onDeleteClick: () -> Unit,
    scrollBehavior: TopAppBarScrollBehavior
) {
    if (selectedItems == 0) {
        ThreadsTopBarDefault(onClick = onSearchClick)
    } else {
        ThreadsTopBarSelection(
            selectedItems = selectedItems,
            onCloseClick = onCloseClick,
            onDeleteClick = onDeleteClick,
            scrollBehavior = scrollBehavior
        )
    }
}
