package com.tinuade.learningapp.di

import com.tinuade.learningapp.data.local.dao.SubjectsDao
import com.tinuade.learningapp.data.remote.LearningRemoteDataSource
import com.tinuade.learningapp.repository.SubjectsRepository
import com.tinuade.learningapp.views.fragments.subject.SubjectViewModel
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent
import dagger.hilt.android.scopes.ViewModelScoped

@Module
@InstallIn(ViewModelComponent::class)
class RepositoryModule {

    @Provides
    @ViewModelScoped
    fun provideSubjectRepository(
        remoteDataSource: LearningRemoteDataSource,
        subjectsDao: SubjectsDao
    ) =
        SubjectsRepository(remoteDataSource, subjectsDao)


    @Provides
    @ViewModelScoped
    fun provideSubjectViewModel(
        subjectsRepository: SubjectsRepository
    ): SubjectViewModel {
        return SubjectViewModel(subjectsRepository)
    }
}