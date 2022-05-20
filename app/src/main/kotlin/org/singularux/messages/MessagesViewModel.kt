package org.singularux.messages

import android.app.Application
import android.provider.Telephony
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.withContext

class MessagesViewModel(app: Application) : AndroidViewModel(app) {

    /* Variables */

    private val mutableThreads = MutableLiveData<List<ThreadItem>>()
    private val mutableSearchResults = MutableLiveData<List<SearchResultItem>>()
    private val threadsObserver = ThreadsObserver(app.applicationContext, mutableThreads)

    /* Init/destroy */

    fun onInit() {
        getApplication<Application>().contentResolver.registerContentObserver(
            Telephony.MmsSms.CONTENT_CONVERSATIONS_URI, true, threadsObserver
        )
        threadsObserver.dispatchChange(false, Telephony.MmsSms.CONTENT_CONVERSATIONS_URI)
    }

    override fun onCleared() {
        getApplication<Application>().contentResolver.unregisterContentObserver(threadsObserver)
    }

    /* Load functions */

    private var searchJob: Job? = null

    fun search(filter: String) {
        // TODO: Run coroutine that does a search
    }

    fun clearSearch() {
        searchJob?.cancel()
        mutableSearchResults.postValue(null)
    }

    /* Getters */

    val threads: LiveData<List<ThreadItem>> get() = mutableThreads
    val searchResults: LiveData<List<SearchResultItem>> get() = mutableSearchResults

}
