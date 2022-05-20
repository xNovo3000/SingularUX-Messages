package org.singularux.messages.ui.component

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.runtime.Composable
import com.google.accompanist.permissions.ExperimentalPermissionsApi
import com.google.accompanist.permissions.PermissionState
import com.google.accompanist.permissions.isGranted
import org.singularux.messages.ThreadItem

@ExperimentalPermissionsApi
@ExperimentalFoundationApi
@ExperimentalMaterial3Api
@Composable
fun ThreadsContent(
    threads: List<ThreadItem>?,
    selectedThreads: Set<Long>,
    innerPadding: PaddingValues,
    permissionState: PermissionState,
    onThreadClick: (ThreadItem) -> Unit,
    onThreadLongClick: (ThreadItem) -> Unit,
) {
    when {
        !permissionState.status.isGranted -> {
            ThreadsContentMissingPermission(
                innerPadding = innerPadding,
                permissionState = permissionState
            )
        }
        threads?.isNotEmpty() == true -> {
            ThreadsContentNotEmpty(
                threads = threads,
                selectedThreads = selectedThreads,
                innerPadding = innerPadding,
                onThreadClick = onThreadClick,
                onThreadLongClick = onThreadLongClick
            )
        }
        else -> {
            ThreadsContentEmpty(innerPadding = innerPadding)
        }
    }
}