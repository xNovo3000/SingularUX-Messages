package org.singularux.messages

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData

class MessagesViewModel(app: Application) : AndroidViewModel(app) {

    private val mutableThreads = MutableLiveData<List<ThreadItem>>()

    fun onInit() {

    }

    override fun onCleared() {

    }

    val threads: LiveData<List<ThreadItem>>
        get() = mutableThreads

}