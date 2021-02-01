package com.tinuade.learningapp.data.local.dao

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.tinuade.learningapp.data.entities.RecentlyWatchedVideos

@Dao
interface RecentlyWatchedVideosDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertALL(recentView: RecentlyWatchedVideos)

    @Query("SELECT * FROM recent LIMIT :num")
    fun getRecentlyWatched(num: Int): LiveData<List<RecentlyWatchedVideos>>
}