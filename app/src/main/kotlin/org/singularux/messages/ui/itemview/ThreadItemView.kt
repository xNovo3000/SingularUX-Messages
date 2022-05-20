package org.singularux.messages.ui.itemview

import android.content.res.Configuration.UI_MODE_NIGHT_NO
import android.content.res.Configuration.UI_MODE_NIGHT_YES
import androidx.compose.foundation.*
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.AccountCircle
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import coil.request.ImageRequest
import org.singularux.messages.ThreadItem
import org.singularux.messages.ui.theme.MessagesTheme
import java.time.Instant
import java.time.LocalDate
import java.time.LocalDateTime
import java.time.ZoneOffset
import java.time.format.DateTimeFormatter

@ExperimentalFoundationApi
@ExperimentalMaterial3Api
@Composable
fun ThreadItemView(
    threadItem: ThreadItem,
    isSelected: Boolean,
    onClick: (ThreadItem) -> Unit,
    onLongClick: (ThreadItem) -> Unit
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .heightIn(min = 64.dp)
            .background(color = if (isSelected) MaterialTheme.colorScheme.surfaceVariant else MaterialTheme.colorScheme.surface)
            .combinedClickable(
                onClick = { onClick(threadItem) },
                onLongClick = { onLongClick(threadItem) }
            )
            .padding(
                horizontal = 16.dp,
                vertical = 8.dp
            ),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Card(
            modifier = Modifier.size(40.dp),
            shape = RoundedCornerShape(20.dp)
        ) {
            Box(modifier = Modifier.size(40.dp)) {
                Image(
                    modifier = Modifier.size(40.dp),
                    imageVector = Icons.Rounded.AccountCircle,
                    contentDescription = null
                )
                AsyncImage(
                    modifier = Modifier.size(40.dp),
                    model = ImageRequest.Builder(LocalContext.current)
                        .size(with(LocalDensity.current) { 40.dp.toPx().toInt() })
                        .data(threadItem.thumbnailUri)
                        .crossfade(250)
                        .build(),
                    contentDescription = null
                )
            }
        }
        Spacer(modifier = Modifier.width(16.dp))
        Column {
            Row {
                Text(
                    modifier = Modifier.weight(1F),
                    text = threadItem.displayName ?: threadItem.phoneNumber,
                    style = MaterialTheme.typography.bodyLarge,
                    overflow = TextOverflow.Ellipsis,
                    maxLines = 1
                )
                Text(
                    modifier = Modifier.padding(start = 16.dp),
                    text = formatInstant(threadItem.lastUpdated),
                    style = MaterialTheme.typography.bodySmall,
                    overflow = TextOverflow.Ellipsis,
                    maxLines = 1
                )
            }
            Row(verticalAlignment = Alignment.CenterVertically) {
                Text(
                    modifier = Modifier.weight(1F),
                    text = threadItem.snippet ?: "MMS",
                    style = MaterialTheme.typography.bodyMedium,
                    overflow = TextOverflow.Ellipsis,
                    maxLines = 1
                )
                if (threadItem.isUnread) {
                    val primary = MaterialTheme.colorScheme.primary
                    Canvas(
                        modifier = Modifier
                            .padding(start = 8.dp)
                            .size(8.dp)
                    ) {
                        drawCircle(color = primary)
                    }
                }
            }
        }
    }
}

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

@Preview(uiMode = UI_MODE_NIGHT_NO)
@Preview(uiMode = UI_MODE_NIGHT_YES)
@ExperimentalFoundationApi
@ExperimentalMaterial3Api
@Composable
private fun Preview() {
    MessagesTheme {
        Surface {
            ThreadItemView(
                threadItem = ThreadItem(
                    id = 1,
                    phoneNumber = "+393334455777",
                    displayName = "Name Surname",
                    snippet = "This is a small snippet",
                    thumbnailUri = "content://thumb/1",
                    lastUpdated = Instant.now(),
                    isUnread = true
                ),
                isSelected = false,
                onClick = {},
                onLongClick = {}
            )
        }
    }
}
