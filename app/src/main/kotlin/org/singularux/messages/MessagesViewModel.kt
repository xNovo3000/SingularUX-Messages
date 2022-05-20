package org.singularux.messages

import android.app.Application
import android.provider.Telephony
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData

class MessagesViewModel(app: Application) : AndroidViewModel(app) {

    private val mutableThreads = MutableLiveData<List<ThreadItem>>()
    private val threadsObserver = ThreadsObserver(app.applicationContext, mutableThreads)

    fun onInit() {
        getApplication<Application>().contentResolver.registerContentObserver(
            Telephony.MmsSms.CONTENT_CONVERSATIONS_URI, true, threadsObserver
        )
        threadsObserver.dispatchChange(false, Telephony.MmsSms.CONTENT_CONVERSATIONS_URI)
    }

    override fun onCleared() {
        getApplication<Application>().contentResolver.unregisterContentObserver(threadsObserver)
    }

    val threads: LiveData<List<ThreadItem>>
        get() = mutableThreads

}