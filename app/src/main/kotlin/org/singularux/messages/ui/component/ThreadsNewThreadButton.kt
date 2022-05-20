package org.singularux.messages.ui.component

import android.content.res.Configuration
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.Add
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import org.singularux.messages.R
import org.singularux.messages.ui.theme.MessagesTheme

@Composable
fun ThreadsNewThreadButton(onClick: () -> Unit) {
    ExtendedFloatingActionButton(
        icon = {
            Icon(
                imageVector = Icons.Rounded.Add,
                contentDescription = null
            )
        },
        text = {
            Text(text = stringResource(id = R.string.threads_new_thread))
        },
        onClick = onClick
    )
}

@Preview(uiMode = Configuration.UI_MODE_NIGHT_NO)
@Preview(uiMode = Configuration.UI_MODE_NIGHT_YES)
@ExperimentalMaterial3Api
@Composable
private fun Preview() {
    MessagesTheme {
        Surface {
            ThreadsNewThreadButton(onClick = {})
        }
    }
}
