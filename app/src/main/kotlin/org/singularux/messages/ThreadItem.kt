package org.singularux.messages

import java.time.Instant

data class ThreadItem(
    val id: Long,
    val phoneNumber: String,
    val displayName: String?,
    val snippet: String?,
    val thumbnailUri: String?,
    val lastUpdated: Instant,
    val isUnread: Boolean
)
