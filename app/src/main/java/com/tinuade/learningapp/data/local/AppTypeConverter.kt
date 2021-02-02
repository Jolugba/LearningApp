package com.tinuade.learningapp.data.local

import androidx.room.TypeConverter
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import com.tinuade.learningapp.data.entities.Chapter
import com.tinuade.learningapp.data.entities.Lesson
import com.tinuade.learningapp.data.entities.Subject

class AppTypeConverter {
    // Json conversion
    @TypeConverter
    fun fromChapterJson(chapters: List<Chapter>): String {
        val gson = Gson()
        return gson.toJson(chapters)
    }


    @TypeConverter
    fun toChapterJson(chapter: String): List<Chapter> {
        val gson = Gson()
        val type = object :
            TypeToken<List<Chapter>>() {}.type
        return gson.fromJson(chapter, type)
    }

    @TypeConverter
    fun fromLessonJson(lesson: List<Lesson>): String {
        val gson = Gson()
        return gson.toJson(lesson)
    }

    @TypeConverter
    fun toLessonJson(lesson: String): List<Lesson> {
        val gson = Gson()
        val type = object :
            TypeToken<Lesson>() {}.type
        return gson.fromJson(lesson, type)
    }

    @TypeConverter
    fun fromSubject(subject: List<Subject>): String {
        val gson = Gson()
        return gson.toJson(subject)
    }

    @TypeConverter
    fun toSubject(subject: String): List<Subject> {
        val gson = Gson()
        val type = object :
            TypeToken<Subject>() {}.type
        return gson.fromJson(subject, type)
    }

}