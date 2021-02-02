package com.tinuade.learningapp.repository

import androidx.lifecycle.LiveData
import com.tinuade.learningapp.data.entities.RecentlyWatchedVideos
import com.tinuade.learningapp.data.local.dao.RecentlyWatchedVideosDao
import javax.inject.Inject

class RecentlyWatchedRespository @Inject constructor(
    private val recentlyWatchedVideosDao: RecentlyWatchedVideosDao
) : Repository {

    override suspend fun insertRecentVideos(recentlyWatchedVideos: RecentlyWatchedVideos) {
        recentlyWatchedVideosDao.insertALL(recentlyWatchedVideos)
    }

    override fun getRecentWatchedVideo(limit: Int): LiveData<List<RecentlyWatchedVideos>> {
        return recentlyWatchedVideosDao.getRecentlyWatched(limit)
    }


}