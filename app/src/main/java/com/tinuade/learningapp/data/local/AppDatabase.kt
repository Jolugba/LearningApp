package com.tinuade.learningapp.data.local

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.tinuade.learningapp.data.entities.Chapter
import com.tinuade.learningapp.data.entities.Lesson
import com.tinuade.learningapp.data.entities.RecentlyWatchedVideos
import com.tinuade.learningapp.data.entities.Subject
import com.tinuade.learningapp.data.local.dao.ChapterDao
import com.tinuade.learningapp.data.local.dao.RecentlyWatchedVideosDao
import com.tinuade.learningapp.data.local.dao.SubjectsDao
import com.tinuade.learningapp.data.local.dao.VideoLessonsDao

@Database(
    entities = [Chapter::class, Lesson::class, Subject::class, RecentlyWatchedVideos::class],
    version = 2,
    exportSchema = false
)
@TypeConverters(AppTypeConverter::class)
abstract class AppDatabase : RoomDatabase() {

    abstract fun chaptersDao(): ChapterDao
    abstract fun recentlyWatchedVideosDao(): RecentlyWatchedVideosDao
    abstract fun subjectsDao(): SubjectsDao
    abstract fun videoLessonDao(): VideoLessonsDao


}