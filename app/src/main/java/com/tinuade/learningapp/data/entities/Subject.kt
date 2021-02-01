package com.tinuade.learningapp.data.entities

import android.os.Parcelable
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize

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
