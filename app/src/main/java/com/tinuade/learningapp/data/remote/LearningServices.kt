package com.tinuade.learningapp.data.remote

import com.tinuade.learningapp.data.LearningApiResponse
import retrofit2.Response
import retrofit2.http.GET

interface LearningServices {
    @GET("content/grade")
    suspend fun getAllSubjects(): Response<LearningApiResponse>


}