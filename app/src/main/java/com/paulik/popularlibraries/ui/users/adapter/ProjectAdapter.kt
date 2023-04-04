package com.paulik.popularlibraries.ui.users.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.paulik.popularlibraries.databinding.ItemGitProjectBinding
import com.paulik.popularlibraries.domain.entity.project.ProjectGitHubEntity

class ProjectAdapter(
    private val itemClickListener: (ProjectGitHubEntity) -> Unit
) : ListAdapter<ProjectGitHubEntity, ProjectAdapter.ProjectViewHolder>(GitHibProjectCallback) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ProjectViewHolder {
        return ProjectViewHolder(
            ItemGitProjectBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        )
    }

    override fun onBindViewHolder(holder: ProjectViewHolder, position: Int) {
        holder.showProject(currentList[position])
    }

    inner class ProjectViewHolder(private val binding: ItemGitProjectBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun showProject(project: ProjectGitHubEntity) {
            binding.root.setOnClickListener { itemClickListener(project) }

            binding.titleTextView.text = project.name

            if (project.private) {
                binding.privateRepoTextView.text = "Private"
            } else {
                binding.privateRepoTextView.text = "public"
            }
        }
    }
}

object GitHibProjectCallback : DiffUtil.ItemCallback<ProjectGitHubEntity>() {
    // совпадают ли элементы
    override fun areItemsTheSame(
        oldItem: ProjectGitHubEntity,
        newItem: ProjectGitHubEntity
    ): Boolean {
        return oldItem == newItem
    }

    // На сколько элементы совпадают
    override fun areContentsTheSame(
        oldItem: ProjectGitHubEntity,
        newItem: ProjectGitHubEntity
    ): Boolean {
        return oldItem == newItem
    }

}