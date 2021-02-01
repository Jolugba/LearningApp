package com.tinuade.learningapp.data.entities

import android.os.Parcelable
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize

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
