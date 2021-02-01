package com.tinuade.learningapp.views.fragments.subjectdetails

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.tinuade.learningapp.R
import com.tinuade.learningapp.data.entities.Lesson
import com.tinuade.learningapp.data.entities.Subject
import com.tinuade.learningapp.utils.AppConstants.LESSON
import com.tinuade.learningapp.utils.AppConstants.SUBJECT
import kotlinx.android.synthetic.main.fragment_subject_details.*

class SubjectDetailsFragment : Fragment(), LessonsAdapter.LessonClickedListener {

    private lateinit var chapterAdapter: ChapterAdapter

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_subject_details, container, false)
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        upButton.setOnClickListener {
            findNavController().navigateUp()
        }

        //get passed subject bundle
        val bundle = arguments
        if (bundle != null) {
            val subjectItem = bundle.getParcelable<Subject>(SUBJECT)
            val subjectTitle = subjectItem?.name
            val allChapters = subjectItem?.chapters
            subjectOverHeadTitle.text = subjectTitle

            chapterAdapter = ChapterAdapter(this)
            allChapters?.let { chapterAdapter.setListItems(it) }
            subjectListRecyclewView.layoutManager = LinearLayoutManager(context)
            subjectListRecyclewView.adapter = chapterAdapter

        }

    }

    override fun onLessonClicked(view: View, item: Lesson) {
        //navigate to play video page
        val bundle = Bundle()
        bundle.putParcelable(LESSON, item)
        findNavController().navigate(
            R.id.action_subjectDetailsFragment_to_playLessonFragment,
            bundle
        )
    }
}
