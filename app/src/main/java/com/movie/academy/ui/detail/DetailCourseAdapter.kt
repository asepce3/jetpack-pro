package com.movie.academy.ui.detail

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.movie.academy.data.ModuleEntity
import com.movie.academy.databinding.ItemsModuleListBinding

class DetailCourseAdapter : RecyclerView.Adapter<DetailCourseAdapter.DetailCourseViewHolder>() {

    class DetailCourseViewHolder(private val binding: ItemsModuleListBinding) : RecyclerView.ViewHolder(binding.root) {

        fun bind(module: ModuleEntity) {
            binding.textModuleTitle.text = module.title
        }
    }

    private val listModules = arrayListOf<ModuleEntity>()

    fun setModules(courses: List<ModuleEntity>) {
        listModules.clear()
        listModules.addAll(courses)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DetailCourseViewHolder {
        val binding = ItemsModuleListBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return DetailCourseViewHolder(binding)
    }

    override fun onBindViewHolder(holder: DetailCourseViewHolder, position: Int) {
        holder.bind(listModules[position])
    }

    override fun getItemCount(): Int = listModules.size
}