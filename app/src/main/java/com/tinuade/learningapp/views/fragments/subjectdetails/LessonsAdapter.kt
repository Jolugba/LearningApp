package com.tinuade.learningapp.views.fragments.subjectdetails

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.google.android.material.card.MaterialCardView
import com.tinuade.learningapp.R
import com.tinuade.learningapp.data.entities.Lesson
import kotlinx.android.synthetic.main.subject_details_child_list_item.view.*

class LessonsAdapter(private val lessonsClickedListener: LessonClickedListener) :
    RecyclerView.Adapter<LessonsAdapter.LessonViewModel>() {

    private var lessonList = mutableListOf<Lesson>()

    fun setLessonItems(lessonData: List<Lesson>) {
        lessonList.clear()
        lessonList.addAll(lessonData)
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): LessonViewModel {
        return LessonViewModel(
            LayoutInflater.from(parent.context)
                .inflate(R.layout.subject_details_child_list_item, parent, false)
        )
    }

    override fun getItemCount(): Int {
        return lessonList.size
    }

    override fun onBindViewHolder(holder: LessonViewModel, position: Int) {
        val item = lessonList[position]
        val lessonName = item.name
        holder.lessonTitle.text = lessonName


        val icon = item.icon
        //load image url into imageView using picasso
        Glide.with(holder.imageView.context)
            .load(icon)
            .into(holder.imageView)

        holder.lessonItem.setOnClickListener {
            lessonsClickedListener.onLessonClicked(it, item)
        }
    }

    class LessonViewModel(view: View) : RecyclerView.ViewHolder(view) {
        val imageView: ImageView = view.subjectLessonImage
        val lessonTitle: TextView = view.subjectLessonTitle
        val lessonItem: MaterialCardView = view.subjectItem
    }

    interface LessonClickedListener {
        fun onLessonClicked(view: View, item: Lesson)
    }

}