package com.tinuade.learningapp.utils

import androidx.lifecycle.LiveData
import androidx.lifecycle.liveData
import androidx.lifecycle.map
import kotlinx.coroutines.Dispatchers


fun <T, A> performGetOperation(
    databaseQuery: () -> LiveData<T>,
    networkCall: suspend () -> Resources<A>,
    saveCallResult: suspend (A) -> Unit
): LiveData<Resources<T>> =
    liveData(Dispatchers.IO) {
        emit(Resources.loading())
        val source = databaseQuery.invoke().map { Resources.success(it) }
        emitSource(source)

        val responseStatus = networkCall.invoke()
        if (responseStatus.status == Resources.Status.SUCCESS) {
            saveCallResult(responseStatus.data!!)

        } else if (responseStatus.status == Resources.Status.ERROR) {
            emit(Resources.error(responseStatus.message!!))
            emitSource(source)
        }
    }