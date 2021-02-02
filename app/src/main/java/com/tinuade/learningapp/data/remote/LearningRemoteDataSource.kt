package com.tinuade.learningapp.data.remote

import javax.inject.Inject

class LearningRemoteDataSource @Inject constructor(
    private val learningServices: LearningServices
) : LearningDataSource() {

    suspend fun getSubject() = getResult { learningServices.getAllSubjects() }

}