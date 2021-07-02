package com.movie.academy.ui.academy

import android.content.Intent
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.movie.academy.R
import com.movie.academy.data.CourseEntity
import com.movie.academy.databinding.ItemsAcademyBinding
import com.movie.academy.ui.detail.DetailCourseActivity

class AcademyAdapter : RecyclerView.Adapter<AcademyAdapter.AcademyViewHolder>() {

    private val listCourses = arrayListOf<CourseEntity>()

    fun setCourses(courses: List<CourseEntity>) {
        listCourses.clear()
        listCourses.addAll(courses)
    }

    class AcademyViewHolder(private val binding: ItemsAcademyBinding) : RecyclerView.ViewHolder(binding.root) {

        fun bind(course: CourseEntity) {
            with(binding) {
                tvItemTitle.text = course.title
                tvItemDate.text = itemView.resources.getString(R.string.deadline_date, course.deadline)
                itemView.setOnClickListener {
                    val intent = Intent(itemView.context, DetailCourseActivity::class.java)
                    intent.putExtra(DetailCourseActivity.EXTRA_COURSE, course.courseId)
                    itemView.context.startActivity(intent)
                }
                Glide.with(itemView.context)
                    .load(course.imagePath)
                    .apply(RequestOptions.placeholderOf(R.drawable.ic_loading))
                    .error(R.drawable.ic_error)
                    .into(imgPoster)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): AcademyViewHolder {
        val view: ItemsAcademyBinding = ItemsAcademyBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return AcademyViewHolder(view)
    }

    override fun onBindViewHolder(holder: AcademyViewHolder, position: Int) {
        holder.bind(listCourses[position])
    }

    override fun getItemCount(): Int = listCourses.size
}