package com.tinuade.learningapp.views.fragments.playvideo

import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.tinuade.learningapp.data.entities.RecentlyWatchedVideos
import com.tinuade.learningapp.repository.Repository
import dagger.hilt.android.scopes.ViewModelScoped
import kotlinx.coroutines.launch

@ViewModelScoped
class PlayVideoViewModel @ViewModelInject constructor(val repository: Repository) : ViewModel() {

    fun addRecentWatched(recentlyWatchedVideos: RecentlyWatchedVideos) {
        viewModelScope.launch {
            repository.insertRecentVideos(recentlyWatchedVideos)
        }
    }
}