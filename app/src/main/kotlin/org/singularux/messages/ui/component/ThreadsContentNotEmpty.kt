package org.singularux.messages.ui.component

import android.content.res.Configuration
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import org.singularux.messages.ThreadItem
import org.singularux.messages.ui.itemview.ThreadItemView
import org.singularux.messages.ui.theme.MessagesTheme

@ExperimentalFoundationApi
@ExperimentalMaterial3Api
@Composable
fun ThreadsContentNotEmpty(
    threads: List<ThreadItem>,
    selectedThreads: Set<Long>,
    innerPadding: PaddingValues,
    onThreadClick: (ThreadItem) -> Unit,
    onThreadLongClick: (ThreadItem) -> Unit,
) {
    LazyColumn(
        modifier = Modifier.fillMaxSize(),
        contentPadding = innerPadding
    ) {
        items(
            items = threads,
            key = { it.id }
        ) {
            ThreadItemView(
                threadItem = it,
                isSelected = selectedThreads.contains(it.id),
                onClick = onThreadClick,
                onLongClick = onThreadLongClick
            )
        }
        item { Spacer(modifier = Modifier.height(88.dp)) }
    }
}

@Preview(uiMode = Configuration.UI_MODE_NIGHT_NO)
@Preview(uiMode = Configuration.UI_MODE_NIGHT_YES)
@ExperimentalFoundationApi
@ExperimentalMaterial3Api
@Composable
private fun Preview() {
    MessagesTheme {
        Surface {
            ThreadsContentNotEmpty(
                threads = listOf(),
                selectedThreads = setOf(),
                innerPadding = PaddingValues.Absolute(),
                onThreadClick = {},
                onThreadLongClick = {}
            )
        }
    }
}