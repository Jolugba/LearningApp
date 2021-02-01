package com.tinuade.learningapp.views.fragments.subject

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.VisibleForTesting
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.GridLayoutManager
import com.tinuade.learningapp.R
import com.tinuade.learningapp.adapters.SubjectsAdapter
import com.tinuade.learningapp.data.entities.Subject
import com.tinuade.learningapp.utils.Message
import com.tinuade.learningapp.utils.Resources
import com.tinuade.learningapp.utils.ViewExtension.hide
import com.tinuade.learningapp.utils.ViewExtension.show
import com.tinuade.learningapp.utils.ViewExtension.showMessage
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.android.synthetic.main.fragment_dash_board.*

@AndroidEntryPoint
class SubjectFragment : Fragment(), SubjectsAdapter.SubjectClickedListener {
    @VisibleForTesting
    private val subjectViewModel: SubjectViewModel by viewModels()
    private lateinit var adapter: SubjectsAdapter

    companion object {
        @JvmStatic
        fun newInstance() = SubjectFragment()
    }


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_dash_board, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupRecyclerView()
        setUpObserver()
    }

    private fun setupRecyclerView() {
        adapter = SubjectsAdapter(this)
        subjectRecyclerView.layoutManager = GridLayoutManager(
            context, 2, GridLayoutManager.VERTICAL, false
        )
        subjectRecyclerView.adapter = adapter
    }

    private fun setUpObserver() {
        subjectViewModel.characters.observe(viewLifecycleOwner, {
            when (it.status) {
                Resources.Status.SUCCESS -> {
                    appLoader.hide()
                    if (!it.data.isNullOrEmpty()) adapter.setListItems(ArrayList(it.data))
                    else subjectRecyclerView.showMessage(Message(it.message!!, true))
                }
                Resources.Status.ERROR -> {
                    subjectRecyclerView.showMessage(Message(it.message!!, true))
                }

                Resources.Status.LOADING ->
                    appLoader.show()
            }
        })
    }

    override fun onSubjectClicked(view: View, item: Subject) {
        subjectRecyclerView.showMessage(Message("welcome", false))
    }


}
