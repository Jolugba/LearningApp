package com.tinuade.learningapp.views.fragments.subjectdetails

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.tinuade.learningapp.R
import com.tinuade.learningapp.data.entities.Chapter
import com.tinuade.learningapp.data.entities.Lesson
import kotlinx.android.synthetic.main.subject_detail_list_items.view.*

class ChapterAdapter(val listener: LessonsAdapter.LessonClickedListener) :
    RecyclerView.Adapter<ChapterAdapter.ChaptersViewModel>(),
    LessonsAdapter.LessonClickedListener {

    private var chapterList = mutableListOf<Chapter>()

    fun setListItems(subjectData: List<Chapter>) {
        chapterList.clear()
        chapterList.addAll(subjectData)
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ChaptersViewModel {
        return ChaptersViewModel(
            LayoutInflater.from(parent.context)
                .inflate(R.layout.subject_detail_list_items, parent, false)
        )
    }

    override fun getItemCount(): Int {
        return chapterList.size
    }

    override fun onBindViewHolder(holder: ChaptersViewModel, position: Int) {
        val item = chapterList[position]
        holder.chapterTitle.text = item.name
        val allLessonsInChapter = item.lessons

        //populate lesson recyclerview
        val lessonLayoutManager = LinearLayoutManager(
            holder.lessonRecyclerView.context, LinearLayoutManager.HORIZONTAL, false
        )

        val lessonsAdapter by lazy { LessonsAdapter(this) }
        allLessonsInChapter.let { lessonsAdapter.setLessonItems(allLessonsInChapter) }
        holder.lessonRecyclerView.layoutManager = lessonLayoutManager
        holder.lessonRecyclerView.adapter = lessonsAdapter

    }

    class ChaptersViewModel(view: View) : RecyclerView.ViewHolder(view) {
        val chapterTitle: TextView = view.subjectDetailTitle
        val lessonRecyclerView: RecyclerView = view.subjectTitleChildRecyclerview
    }

    override fun onLessonClicked(view: View, item: Lesson) {
        //pass lesson item to chapters page
        listener.onLessonClicked(view, item)

    }
}