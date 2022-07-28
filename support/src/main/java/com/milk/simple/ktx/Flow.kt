package com.milk.simple.ktx

import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.coroutineScope
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch

fun <T> Flow<T>.collectLatest(lifecycleOwner: LifecycleOwner, resultRequest: (T) -> Unit) {
    lifecycleOwner.lifecycle.coroutineScope.launch {
        this@collectLatest.collectLatest {
            resultRequest(it)
        }
    }
}

fun <T> Flow<T>.collect(lifecycleOwner: LifecycleOwner, resultRequest: (T) -> Unit) {
    lifecycleOwner.lifecycle.coroutineScope.launch {
        this@collect.collect {
            resultRequest(it)
        }
    }
}