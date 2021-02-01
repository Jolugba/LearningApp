package com.tinuade.learningapp.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.google.android.material.card.MaterialCardView
import com.tinuade.learningapp.R
import com.tinuade.learningapp.data.entities.Subject
import kotlinx.android.synthetic.main.subject_list_items.view.*

class SubjectsAdapter(private val listener: SubjectClickedListener) :
    RecyclerView.Adapter<SubjectsAdapter.SubjectsViewHolder>() {

    private var subjectList = mutableListOf<Subject>()

    fun setListItems(subjectData: List<Subject>) {
        subjectList.clear()
        subjectList.addAll(subjectData)
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SubjectsViewHolder {
        return SubjectsViewHolder(
            LayoutInflater.from(parent.context)
                .inflate(R.layout.subject_list_items, parent, false)
        )
    }

    override fun getItemCount(): Int {
        return subjectList.size
    }

    override fun onBindViewHolder(holder: SubjectsViewHolder, position: Int) {
        val item = subjectList[position]
        holder.subjectTitle.text = item.name

        val icon = item.icon
        //load image url into imageView using picasso
        Glide.with(holder.subjectIcon.context)
            .load(icon)
            .into(holder.subjectIcon)

        holder.subjectCardItem.setOnClickListener {
            listener.onSubjectClicked(it, item)
        }
        when (item.name) {
            "Mathematics" -> {
                holder.subjectLayout.setBackgroundResource(R.drawable.ic_mathematics_background)
            }
            "Biology" -> {
                holder.subjectLayout.setBackgroundResource(R.drawable.bio_background)
            }
            "Chemistry" -> {
                holder.subjectLayout.setBackgroundResource(R.drawable.ic_chm_background)
            }
            "English" -> {
                holder.subjectLayout.setBackgroundResource(R.drawable.ic_eng_background)
            }
            "Physics" -> {
                holder.subjectLayout.setBackgroundResource(R.drawable.ic_phy_background)
            }
        }

    }

    class SubjectsViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val subjectTitle: TextView = view.subjectTitle
        val subjectIcon: ImageView = view.subjectIcons
        val subjectCardItem: MaterialCardView = view.cardItem
        val subjectLayout: ConstraintLayout = view.subjectLayout
    }

    interface SubjectClickedListener {
        fun onSubjectClicked(view: View, item: Subject)
    }

}