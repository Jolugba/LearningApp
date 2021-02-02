package com.tinuade.learningapp.repository

import com.tinuade.learningapp.data.local.dao.SubjectsDao
import com.tinuade.learningapp.data.remote.LearningRemoteDataSource
import com.tinuade.learningapp.utils.performGetOperation
import javax.inject.Inject

class SubjectsRepository @Inject constructor(
    private val remoteDataSource: LearningRemoteDataSource,
    private val subjectsDao: SubjectsDao
) {

    fun getSubjects() = performGetOperation(
        databaseQuery = { subjectsDao.fetchAllSubjects() },
        networkCall = { remoteDataSource.getSubject() }
    ) { subjectsDao.insertAllSubjects(it.data.subjects) }


}