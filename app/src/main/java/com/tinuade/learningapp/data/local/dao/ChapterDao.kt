package com.tinuade.learningapp.data.local.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.tinuade.learningapp.data.entities.Chapter

@Dao
interface ChapterDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertChapterList(chapters: List<Chapter>)

    @Query("SELECT * FROM Chapter WHERE id= :subject_id")
    suspend fun getChapterList(subject_id: Int): List<Chapter>

}