package org.singularux.messages.ui.component

import android.content.res.Configuration
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import org.singularux.messages.SearchResultItem
import org.singularux.messages.ui.theme.MessagesTheme

@Composable
fun SearchContent(
    searchResults: List<SearchResultItem>?,
    innerPadding: PaddingValues,
    onThreadClick: (SearchResultItem) -> Unit,
) {
    if (searchResults != null) {
        SearchContentNotEmpty(
            searchResults = searchResults,
            innerPadding = innerPadding,
            onThreadClick = onThreadClick
        )
    } else {
        SearchContentEmpty(innerPadding = innerPadding)
    }
}
