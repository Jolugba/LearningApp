package com.tinuade.learningapp.views.fragments.subject

import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.switchMap
import com.tinuade.learningapp.repository.RecentlyWatchedRespository
import com.tinuade.learningapp.repository.SubjectsRepository


class SubjectViewModel @ViewModelInject constructor(
    repository: SubjectsRepository,
    recentlyWatchedRespository: RecentlyWatchedRespository
) : ViewModel() {

    private val buttonText = MutableLiveData<String>()
    val _buttonText: LiveData<String> = buttonText

    val subject = repository.getSubjects()

    val recentWatchVideos = _buttonText.switchMap {
        when (it) {
            "VIEW ALL" -> recentlyWatchedRespository.getRecentWatchedVideo(2)
            else -> recentlyWatchedRespository.getRecentWatchedVideo(20)
        }
    }


    fun buttonBehaviour(text: String) {
        when (text) {
            " View all" -> {
                buttonText.value = "VIEW LESS"
            }
            "VIEW LESS" -> {
                buttonText.value = "VIEW ALL"
            }
        }
    }


}