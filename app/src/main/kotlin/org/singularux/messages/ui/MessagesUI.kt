package org.singularux.messages.ui

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.google.accompanist.permissions.ExperimentalPermissionsApi
import org.singularux.messages.ui.route.SearchRoute
import org.singularux.messages.ui.route.ThreadsRoute
import org.singularux.messages.ui.theme.MessagesTheme

sealed class Route(val name: String) {
    object Threads : Route("threads")
    object Search : Route("search")
    object NewContact : Route("new_contact")
}

@ExperimentalPermissionsApi
@ExperimentalFoundationApi
@ExperimentalMaterial3Api
@Composable
fun MessagesUI() {
    // Apply the theme globally
    MessagesTheme {
        // Add the navigation component
        val navController = rememberNavController()
        NavHost(navController = navController, startDestination = Route.Threads.name) {
            composable(Route.Threads.name) { ThreadsRoute(navController = navController) }
            composable(Route.Search.name) { SearchRoute(navController = navController) }
        }
    }
}