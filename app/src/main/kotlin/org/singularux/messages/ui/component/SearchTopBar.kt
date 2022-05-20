package org.singularux.messages.ui.component

import android.content.res.Configuration
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.ArrowBack
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.SolidColor
import androidx.compose.ui.tooling.preview.Preview
import org.singularux.messages.ui.theme.MessagesTheme

@Composable
fun SearchTopBar(
    filterValue: String,
    onBackClick: () -> Unit,
    onFilterChange: (String) -> Unit,
    scrollBehavior: TopAppBarScrollBehavior
) {
    SmallTopAppBar(
        navigationIcon = {
            IconButton(onClick = onBackClick) {
                Icon(
                    imageVector = Icons.Rounded.ArrowBack,
                    contentDescription = null
                )
            }
        },
        title = {
            BasicTextField(
                modifier = Modifier.fillMaxWidth(),
                value = filterValue,
                onValueChange = onFilterChange,
                cursorBrush = SolidColor(LocalContentColor.current),
                textStyle = MaterialTheme.typography.titleLarge.copy(color = LocalContentColor.current),
                maxLines = 1
            )
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
        SearchTopBar(
            filterValue = "Test123",
            onBackClick = {},
            onFilterChange = {},
            scrollBehavior = TopAppBarDefaults.pinnedScrollBehavior()
        )
    }
}
