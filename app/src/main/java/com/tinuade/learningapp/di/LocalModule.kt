package com.tinuade.learningapp.di

import android.content.Context
import androidx.room.Room
import com.tinuade.learningapp.data.local.AppDatabase
import com.tinuade.learningapp.data.local.dao.ChapterDao
import com.tinuade.learningapp.data.local.dao.RecentlyWatchedVideosDao
import com.tinuade.learningapp.data.local.dao.SubjectsDao
import com.tinuade.learningapp.data.local.dao.VideoLessonsDao
import com.tinuade.learningapp.utils.AppConstants.DATABASE_NAME
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class LocalModule {
    @Provides
    @Singleton
    fun provideAppDatabase(@ApplicationContext context: Context): AppDatabase {
        return Room.databaseBuilder(
            context.applicationContext,
            AppDatabase::class.java, DATABASE_NAME
        )
            .fallbackToDestructiveMigration()
            .build()
    }

    @Singleton
    @Provides
    fun provideContext(@ApplicationContext context: Context): Context {
        return context
    }

    @Provides
    @Singleton
    fun provideChapterDao(appDatabase: AppDatabase): ChapterDao {
        return appDatabase.chaptersDao()
    }

    @Provides
    @Singleton
    fun provideSubjectsDao(appDatabase: AppDatabase): SubjectsDao {
        return appDatabase.subjectsDao()
    }

    @Provides
    @Singleton
    fun provideVideoLessonDao(appDatabase: AppDatabase): VideoLessonsDao {
        return appDatabase.videoLessonDao()
    }

    @Provides
    @Singleton
    fun provideRecentlyWatchedDao(appDatabase: AppDatabase): RecentlyWatchedVideosDao {
        return appDatabase.recentlyWatchedVideosDao()
    }
}