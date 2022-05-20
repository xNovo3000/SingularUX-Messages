package org.singularux.messages

import android.Manifest
import android.content.Context
import android.content.pm.PackageManager
import android.database.ContentObserver
import android.net.Uri
import android.os.Handler
import android.os.Looper
import android.provider.Telephony
import androidx.core.database.getStringOrNull
import androidx.lifecycle.MutableLiveData
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch
import java.time.Instant

class ThreadsObserver(
    private val context: Context,
    private val mutableThreads: MutableLiveData<List<ThreadItem>>
) : ContentObserver(Handler(Looper.getMainLooper())) {

    companion object {
        private val PROJECTION = arrayOf("tid", "address", "body", "normalized_date", "read")
        private const val SORT_ORDER = "normalized_date desc"
    }

    private var job: Job? = null

    override fun onChange(selfChange: Boolean) {
        // Check for permissions
        if (context.checkSelfPermission(Manifest.permission.READ_SMS) != PackageManager.PERMISSION_GRANTED) {
            return
        }
        // Execute
        job?.cancel()
        job = CoroutineScope(Dispatchers.IO).launch {
            val result = mutableListOf<ThreadItem>()
            context.contentResolver.query(
                Telephony.MmsSms.CONTENT_CONVERSATIONS_URI,
                PROJECTION,
                null,
                null,
                SORT_ORDER
            ).use {
                while (it?.moveToNext() == true) {
                    result.add(
                        ThreadItem(
                            id = it.getLong(0),
                            phoneNumber = it.getString(1),
                            displayName = null,
                            snippet = it.getStringOrNull(2),
                            thumbnailUri = null,
                            lastUpdated = Instant.ofEpochMilli(it.getLong(3)),
                            isUnread = it.getInt(4) == 0
                        )
                    )
                }
            }
            mutableThreads.postValue(result)
        }
    }

    override fun onChange(selfChange: Boolean, uris: MutableCollection<Uri>, flags: Int) {
        onChange(selfChange)  // Call only one time for API 30+
    }

}