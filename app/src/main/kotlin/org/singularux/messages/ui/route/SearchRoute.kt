package org.singularux.messages.ui.route

import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.*
import androidx.compose.runtime.livedata.observeAsState
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import org.singularux.messages.MessagesViewModel
import org.singularux.messages.ui.component.SearchContent
import org.singularux.messages.ui.component.SearchTopBar

@ExperimentalMaterial3Api
@Composable
fun SearchRoute(
    navController: NavHostController,
    messagesViewModel: MessagesViewModel = viewModel()
) {
    // State holders
    var filterValue by remember { mutableStateOf("") }
    // Nested scrolling connection
    val scrollBehavior = remember { TopAppBarDefaults.pinnedScrollBehavior() }
    // Build
    Scaffold(
        topBar = {
            SearchTopBar(
                filterValue = filterValue,
                onBackClick = { navController.popBackStack() },
                onFilterChange = { filterValue = it },
                scrollBehavior = scrollBehavior
            )
        }
    ) {
        // Set result here
        val result by messagesViewModel.searchResults.observeAsState(listOf())
        // Catch filter change. Clear when removed from composition
        DisposableEffect(filterValue) {
            if (filterValue.isNotBlank()) messagesViewModel.search(filterValue)
            onDispose { messagesViewModel.clearSearch() }
        }
        // Build
        SearchContent(
            searchResults = result,
            innerPadding = it,
            onThreadClick = {}
        )
    }
}