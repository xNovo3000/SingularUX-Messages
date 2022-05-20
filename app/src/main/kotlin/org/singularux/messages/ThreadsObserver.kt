package org.singularux.messages

import android.Manifest
import android.content.Context
import android.content.pm.PackageManager
import android.database.ContentObserver
import android.database.DatabaseUtils
import android.net.Uri
import android.os.Handler
import android.os.Looper
import android.provider.Telephony
import androidx.lifecycle.MutableLiveData
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch

class ThreadsObserver(
    private val context: Context,
    private val mutableThreads: MutableLiveData<List<ThreadItem>>
) : ContentObserver(Handler(Looper.getMainLooper())) {

    companion object {
        private val PROJECTION = arrayOf(Telephony.MmsSms._ID)
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
                arrayOf("*"),
                null,
                null,
                null
            ).use {
                DatabaseUtils.dumpCursor(it)
            }
            mutableThreads.postValue(result)
        }
    }

    override fun onChange(selfChange: Boolean, uris: MutableCollection<Uri>, flags: Int) {
        onChange(selfChange)  // Call only one time for API 30+
    }

}