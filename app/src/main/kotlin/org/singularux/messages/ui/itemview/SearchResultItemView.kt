package org.singularux.messages.ui.itemview

import android.content.res.Configuration
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import org.singularux.messages.SearchResultItem
import org.singularux.messages.ui.theme.MessagesTheme
import java.time.Instant
import java.time.LocalDate
import java.time.LocalDateTime
import java.time.ZoneOffset
import java.time.format.DateTimeFormatter

@Composable
fun SearchResultItemView(
    searchResultItem: SearchResultItem,
    onClick: (SearchResultItem) -> Unit
) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .heightIn(min = 64.dp)
            .clickable { onClick(searchResultItem) }
            .padding(
                horizontal = 16.dp,
                vertical = 8.dp
            ),
        verticalArrangement = Arrangement.Center
    ) {
        Row {
            Text(
                modifier = Modifier.weight(1F),
                text = searchResultItem.title,
                style = MaterialTheme.typography.bodyLarge,
                overflow = TextOverflow.Ellipsis,
                maxLines = 1
            )
            Text(
                modifier = Modifier.padding(start = 16.dp),
                text = formatInstant(searchResultItem.lastUpdated),
                style = MaterialTheme.typography.bodySmall,
                overflow = TextOverflow.Ellipsis,
                maxLines = 1
            )
        }
        Row(verticalAlignment = Alignment.CenterVertically) {
            Text(
                modifier = Modifier.weight(1F),
                text = searchResultItem.snippet,
                style = MaterialTheme.typography.bodyMedium,
                overflow = TextOverflow.Ellipsis,
                maxLines = 1
            )
        }
    }
}

/* TODO: Declared two times. Optimize */

private val dateFormatter = DateTimeFormatter.ofPattern("MMM d")
private val timeFormatter = DateTimeFormatter.ofPattern("HH:mm")

private fun formatInstant(instant: Instant): String {
    val localDateTime = LocalDateTime.ofInstant(instant, ZoneOffset.UTC)
    return if (localDateTime.toLocalDate().isEqual(LocalDate.now())) {
        // Same day
        timeFormatter.format(localDateTime)
    } else {
        // Different day
        "${dateFormatter.format(localDateTime)}: ${timeFormatter.format(localDateTime)}"
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
            SearchResultItemView(
                searchResultItem = SearchResultItem(
                    id = 1,
                    title = "Title",
                    snippet = "Snippet",
                    lastUpdated = Instant.now()
                ),
                onClick = {}
            )
        }
    }
}