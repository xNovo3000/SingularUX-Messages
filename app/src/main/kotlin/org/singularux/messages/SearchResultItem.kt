package org.singularux.messages

import java.time.Instant

data class SearchResultItem(
    val id: Long,
    val title: String,
    val snippet: String,
    val lastUpdated: Instant
)
