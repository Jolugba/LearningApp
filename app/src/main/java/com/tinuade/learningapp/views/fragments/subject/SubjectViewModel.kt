package com.tinuade.learningapp.views.fragments.subject

import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.ViewModel
import com.tinuade.learningapp.repository.SubjectsRepository


class SubjectViewModel @ViewModelInject constructor(
    repository: SubjectsRepository
) : ViewModel() {

    val characters = repository.getSubjects()

}