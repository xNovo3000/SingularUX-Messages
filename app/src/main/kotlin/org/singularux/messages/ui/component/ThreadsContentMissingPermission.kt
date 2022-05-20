package org.singularux.messages.ui.component

import android.Manifest
import android.content.Intent
import android.content.res.Configuration
import android.net.Uri
import android.provider.Settings
import androidx.compose.foundation.layout.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.Block
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.google.accompanist.permissions.ExperimentalPermissionsApi
import com.google.accompanist.permissions.PermissionState
import com.google.accompanist.permissions.rememberPermissionState
import com.google.accompanist.permissions.shouldShowRationale
import org.singularux.messages.R
import org.singularux.messages.ui.theme.MessagesTheme

@ExperimentalPermissionsApi
@Composable
fun ThreadsContentMissingPermission(
    innerPadding: PaddingValues,
    permissionState: PermissionState
) {
    val context = LocalContext.current
    Column(
        modifier = Modifier
            .padding(innerPadding)  // Top bar padding
            .padding(bottom = 88.dp)  // Fab padding
            .fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Icon(
            modifier = Modifier.size(96.dp),
            imageVector = Icons.Rounded.Block,
            contentDescription = null
        )
        Spacer(modifier = Modifier.height(4.dp))
        Text(
            text =  stringResource(id = R.string.threads_missing_permission),
            style = MaterialTheme.typography.bodyLarge
        )
        Spacer(modifier = Modifier.height(4.dp))
        TextButton(
            onClick = {
                if (permissionState.status.shouldShowRationale) {
                    permissionState.launchPermissionRequest()
                } else {
                    context.startActivity(Intent(Settings.ACTION_APPLICATION_DETAILS_SETTINGS).also {
                        it.addCategory(Intent.CATEGORY_DEFAULT)
                        it.data = Uri.parse("package:" + context.packageName)
                    })
                }
            }
        ) {
            Text(text = stringResource(id = R.string.threads_give_permission))
        }
    }
}

@Preview(uiMode = Configuration.UI_MODE_NIGHT_NO)
@Preview(uiMode = Configuration.UI_MODE_NIGHT_YES)
@ExperimentalPermissionsApi
@Composable
private fun Preview() {
    MessagesTheme {
        Surface {
            ThreadsContentMissingPermission(
                innerPadding = PaddingValues.Absolute(),
                permissionState = rememberPermissionState(permission = Manifest.permission.READ_SMS)
            )
        }
    }
}