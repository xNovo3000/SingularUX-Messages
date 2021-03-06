package org.singularux.messages.ui.route

import android.Manifest
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.*
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.input.nestedscroll.nestedScroll
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import com.google.accompanist.permissions.ExperimentalPermissionsApi
import com.google.accompanist.permissions.isGranted
import com.google.accompanist.permissions.rememberPermissionState
import org.singularux.messages.MessagesViewModel
import org.singularux.messages.ui.Route
import org.singularux.messages.ui.component.ThreadsContent
import org.singularux.messages.ui.component.ThreadsNewThreadButton
import org.singularux.messages.ui.component.ThreadsTopBar

@ExperimentalPermissionsApi
@ExperimentalFoundationApi
@ExperimentalMaterial3Api
@Composable
fun ThreadsRoute(
    navController: NavHostController,
    messagesViewModel: MessagesViewModel = viewModel()
) {
    // Permission management
    val readThreadsPermissionState = rememberPermissionState(permission = Manifest.permission.READ_SMS)
    LaunchedEffect(readThreadsPermissionState.status) {
        if (readThreadsPermissionState.status.isGranted) messagesViewModel.onInit()
    }
    // State holders
    var selectedItems by remember { mutableStateOf(setOf<Long>()) }
    val threads by messagesViewModel.threads.observeAsState()
    // Nested scrolling connection
    val scrollBehavior = remember { TopAppBarDefaults.pinnedScrollBehavior() }
    // When threads updates, remove from selectedItems when ID is not present in threads anymore
    LaunchedEffect(threads) {
        val orphans = selectedItems.filter { selectedItem -> threads?.none { selectedItem == it.id } ?: true }
        if (orphans.isNotEmpty()) {
            selectedItems = selectedItems - orphans.toSet()
        }
    }
    // Build
    Scaffold(
        modifier = Modifier.nestedScroll(scrollBehavior.nestedScrollConnection),
        topBar = {
            ThreadsTopBar(
                selectedItems = selectedItems.size,
                onSearchClick = {
                    if (readThreadsPermissionState.status.isGranted) {
                        navController.navigate(Route.Search.name)
                    }
                },
                onCloseClick = { selectedItems = setOf() },
                onDeleteClick = { /* TODO: show delete dialog */ },
                scrollBehavior = scrollBehavior
            )
        },
        floatingActionButton = {
            ThreadsNewThreadButton(
                onClick = {
                    if (readThreadsPermissionState.status.isGranted) {
                        navController.navigate(Route.NewContact.name)
                    }
                }
            )
        }
    ) { innerPadding ->
        ThreadsContent(
            threads = threads,
            selectedThreads = selectedItems,
            innerPadding = innerPadding,
            permissionState = readThreadsPermissionState,
            onThreadClick = {
                if (selectedItems.isEmpty()) {
                    // TODO: Go to thread page
                } else {
                    selectedItems = if (selectedItems.contains(it.id)) {
                        selectedItems - it.id
                    } else {
                        selectedItems + it.id
                    }
                }
            },
            onThreadLongClick = {
                selectedItems = if (selectedItems.contains(it.id)) {
                    selectedItems - it.id
                } else {
                    selectedItems + it.id
                }
            }
        )
    }
}