package com.tinuade.learningapp.repository

import androidx.lifecycle.LiveData
import com.tinuade.learningapp.data.entities.RecentlyWatchedVideos

interface Repository {
    suspend fun insertRecentVideos(recentlyWatchedVideos: RecentlyWatchedVideos)

    fun getRecentWatchedVideo(limit: Int): LiveData<List<RecentlyWatchedVideos>>

}