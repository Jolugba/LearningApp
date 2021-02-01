package com.tinuade.learningapp.data

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import com.tinuade.learningapp.data.entities.Subject
import kotlinx.android.parcel.Parcelize

@Parcelize
data class Data(
    @SerializedName("message")
    val message: String,
    @SerializedName("status")
    val status: String,
    @SerializedName("subjects")
    val subjects: List<Subject>
) : Parcelable
