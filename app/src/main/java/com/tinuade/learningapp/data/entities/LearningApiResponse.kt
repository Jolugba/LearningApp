package com.tinuade.learningapp.data.entities

import android.os.Parcelable
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize

data class LearningApiResponse(
    val `data`: Data
)


@Parcelize
data class Data(
    @SerializedName("message")
    val message: String,
    @SerializedName("status")
    val status: String,
    @SerializedName("subjects")
    val subjects: List<Subject>
) : Parcelable


@Entity
@Parcelize
data class Subject(
    @SerializedName("chapters")
    val chapters: List<Chapter>,
    @SerializedName("icon")
    val icon: String,
    @PrimaryKey
    @SerializedName("id")
    val id: Int,
    @SerializedName("name")
    val name: String
) : Parcelable

@Entity
@Parcelize
data class Lesson(
    @SerializedName("chapter_id")
    val chapter_id: Int,
    @SerializedName("icon")
    val icon: String,
    @PrimaryKey
    @SerializedName("id")
    val id: Int,
    @SerializedName("media_url")
    val media_url: String,
    @SerializedName("name")
    val name: String,
    @SerializedName("subject_id")
    val subject_id: Int
) : Parcelable

@Entity
@Parcelize
data class Chapter(
    @PrimaryKey
    @SerializedName("id")
    val id: Int,
    @SerializedName("lessons")
    val lessons: List<Lesson>,
    @SerializedName("name")
    val name: String
) : Parcelable


@Entity(tableName = "recent")
data class RecentlyWatchedVideos(
    @PrimaryKey
    @SerializedName("chapter_id")
    val id: Int,
    val mediaUrl: String,
    @SerializedName("name")
    val subjectName: String,
    val lessonTitle: String
)
