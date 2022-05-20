package org.singularux.messages.ui.component

import android.content.res.Configuration
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.MoreVert
import androidx.compose.material.icons.rounded.Search
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import org.singularux.messages.R
import org.singularux.messages.ui.theme.MessagesTheme

@ExperimentalMaterial3Api
@Composable
fun ThreadsTopBarDefault(onClick: () -> Unit) {
    ElevatedCard(
        modifier = Modifier
            .padding(
                horizontal = 12.dp,
                vertical = 8.dp
            )
            .fillMaxWidth()
            .heightIn(min = 48.dp),
        shape = RoundedCornerShape(24.dp),
        onClick = onClick
    ) {
        Row(
            modifier = Modifier.padding(horizontal = 4.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Icon(
                modifier = Modifier
                    .size(48.dp)
                    .padding(12.dp),
                imageVector = Icons.Rounded.Search,
                contentDescription = null
            )
            Text(
                modifier = Modifier.weight(1F),
                text = stringResource(id = R.string.threads_search_messages),
                style = MaterialTheme.typography.titleLarge,
                overflow = TextOverflow.Ellipsis,
                fontSize = 18.sp,
                maxLines = 1
            )
            IconButton(onClick = {}) {
                Icon(
                    imageVector = Icons.Rounded.MoreVert,
                    contentDescription = null
                )
            }
        }
    }
}

@Preview(uiMode = Configuration.UI_MODE_NIGHT_NO)
@Preview(uiMode = Configuration.UI_MODE_NIGHT_YES)
@ExperimentalMaterial3Api
@Composable
private fun Preview() {
    MessagesTheme {
        Surface {
            ThreadsTopBarDefault(onClick = {})
        }
    }
}
