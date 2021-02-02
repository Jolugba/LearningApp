package com.tinuade.learningapp.utils

import android.view.View
import androidx.core.content.ContextCompat
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.google.android.material.snackbar.Snackbar
import com.tinuade.learningapp.R
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

object ViewExtension {
    fun View.show() {
        this.visibility = View.VISIBLE
    }

    fun View.hide() {
        this.visibility = View.GONE
    }

    fun ViewModel.runIO(function: suspend CoroutineScope.() -> Unit) {
        viewModelScope.launch(Dispatchers.IO) {
            function()
        }
    }

    fun View.showMessage(message: Message) {
        val snackbar = Snackbar.make(this, message.message, Snackbar.LENGTH_LONG)
        if (message.isError) {
            snackbar.setBackgroundTint(ContextCompat.getColor(this.context, R.color.red))
            snackbar.show()
        } else {
            snackbar.setBackgroundTint(
                ContextCompat.getColor(
                    this.context,
                    R.color.orange_dark_shade
                )
            )
            snackbar.show()
        }
    }
}