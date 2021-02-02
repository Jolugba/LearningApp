package com.tinuade.learningapp.views.fragments.subject

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.tinuade.learningapp.R
import com.tinuade.learningapp.data.entities.RecentlyWatchedVideos
import kotlinx.android.synthetic.main.recent_watched_list_items.view.*
import kotlinx.android.synthetic.main.subject_list_items.view.*

class RecentlyWatchedAdapter :
    RecyclerView.Adapter<RecentlyWatchedAdapter.SubjectsViewHolder>() {

    private var list = mutableListOf<RecentlyWatchedVideos>()

    fun setListItems(subjectData: List<RecentlyWatchedVideos>) {
        list.clear()
        list.addAll(subjectData)
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SubjectsViewHolder {
        return SubjectsViewHolder(
            LayoutInflater.from(parent.context)
                .inflate(R.layout.recent_watched_list_items, parent, false)
        )
    }

    override fun getItemCount(): Int {
        return list.size
    }

    override fun onBindViewHolder(holder: SubjectsViewHolder, position: Int) {
        val item = list[position]
        holder.subjectTitle.text = item.subjectName
        holder.subjectTopic.text = item.lessonTitle


        val icon = item.mediaUrl
        //load image url into imageView using picasso
        Glide.with(holder.imageView.context)
            .load(icon)
            .into(holder.imageView)


    }

    class SubjectsViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val subjectTitle: TextView = view.subjectTitle
        val imageView: ImageView = view.videoImage
        val playButton: ImageView = view.recentlyWatchedPlayButon
        val subjectTopic: TextView = view.subjectTopic
    }

    interface ClickedListener {
        fun onClicked(view: View, item: RecentlyWatchedVideos)
    }

}