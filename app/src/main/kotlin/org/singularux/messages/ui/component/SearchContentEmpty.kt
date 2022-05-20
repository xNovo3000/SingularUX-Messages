package org.singularux.messages.ui.component

import android.content.res.Configuration
import androidx.compose.foundation.layout.*
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import org.singularux.messages.R
import org.singularux.messages.ui.theme.MessagesTheme

@Composable
fun SearchContentEmpty(innerPadding: PaddingValues) {
    Column(
        modifier = Modifier
            .padding(innerPadding)  // Top bar padding
            .fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            text =  stringResource(id = R.string.search_not_found),
            style = MaterialTheme.typography.bodyLarge
        )
    }
}

@Preview(uiMode = Configuration.UI_MODE_NIGHT_NO)
@Preview(uiMode = Configuration.UI_MODE_NIGHT_YES)
@Composable
private fun Preview() {
    MessagesTheme {
        Surface {
            SearchContentEmpty(innerPadding = PaddingValues.Absolute())
        }
    }
}
