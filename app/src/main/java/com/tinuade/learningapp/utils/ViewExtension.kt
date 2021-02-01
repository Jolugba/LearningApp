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
        if (message.isError) {
            val snackbar = Snackbar.make(this, message.message, Snackbar.LENGTH_INDEFINITE)
            snackbar.setBackgroundTint(ContextCompat.getColor(this.context, R.color.red))
            snackbar.show()
        } else {
            val snackbar = Snackbar.make(this, message.message, Snackbar.LENGTH_LONG)
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