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
import androidx.compose.ui.unit.dp
import org.singularux.messages.ui.theme.MessagesTheme
import org.singularux.messages.R

@Composable
fun ThreadsContentEmpty(innerPadding: PaddingValues) {
    Column(
        modifier = Modifier
            .padding(innerPadding)  // Top bar padding
            .padding(bottom = 88.dp)  // Fab padding
            .fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            text =  stringResource(id = R.string.threads_no_threads),
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
            ThreadsContentEmpty(innerPadding = PaddingValues.Absolute())
        }
    }
}
