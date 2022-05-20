package org.singularux.messages.ui.component

import android.content.res.Configuration
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import org.singularux.messages.SearchResultItem
import org.singularux.messages.ui.itemview.SearchResultItemView
import org.singularux.messages.ui.theme.MessagesTheme

@Composable
fun SearchContentNotEmpty(
    searchResults: List<SearchResultItem>,
    innerPadding: PaddingValues,
    onThreadClick: (SearchResultItem) -> Unit,
) {
    LazyColumn(
        modifier = Modifier.fillMaxSize(),
        contentPadding = innerPadding
    ) {
        items(
            items = searchResults,
            key = { it.id }
        ) {
            SearchResultItemView(
                searchResultItem = it,
                onClick = onThreadClick
            )
        }
    }
}

@Preview(uiMode = Configuration.UI_MODE_NIGHT_NO)
@Preview(uiMode = Configuration.UI_MODE_NIGHT_YES)
@Composable
private fun Preview() {
    MessagesTheme {
        Surface {
            SearchContentNotEmpty(
                searchResults = listOf(),
                innerPadding = PaddingValues.Absolute(),
                onThreadClick = {}
            )
        }
    }
}