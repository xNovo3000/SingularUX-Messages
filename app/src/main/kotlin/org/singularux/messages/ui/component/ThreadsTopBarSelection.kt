package org.singularux.messages.ui.component

import android.content.res.Configuration
import androidx.activity.compose.BackHandler
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.Close
import androidx.compose.material.icons.rounded.Delete
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import org.singularux.messages.R
import org.singularux.messages.ui.theme.MessagesTheme

@ExperimentalMaterial3Api
@Composable
fun ThreadsTopBarSelection(
    selectedItems: Int,
    onCloseClick: () -> Unit,
    onDeleteClick: () -> Unit,
    scrollBehavior: TopAppBarScrollBehavior
) {
    BackHandler { onCloseClick() }
    SmallTopAppBar(
        navigationIcon = {
            IconButton(onClick = onCloseClick) {
                Icon(
                    imageVector = Icons.Rounded.Close,
                    contentDescription = null
                )
            }
        }, 
        title = { Text(text = stringResource(id = R.string.threads_n_selected, selectedItems)) },
        actions = {
            IconButton(onClick = onDeleteClick) {
                Icon(
                    imageVector = Icons.Rounded.Delete,
                    contentDescription = null
                )
            }
        },
        scrollBehavior = scrollBehavior
    )
}

@Preview(uiMode = Configuration.UI_MODE_NIGHT_NO)
@Preview(uiMode = Configuration.UI_MODE_NIGHT_YES)
@ExperimentalMaterial3Api
@Composable
private fun Preview() {
    MessagesTheme {
        Surface {
            ThreadsTopBarSelection(
                selectedItems = 0,
                onCloseClick = {},
                onDeleteClick = {},
                scrollBehavior = TopAppBarDefaults.pinnedScrollBehavior()
            )
        }
    }
}