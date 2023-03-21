package com.paulik.popularlibraries.ui.users.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.paulik.popularlibraries.databinding.ItemGitForksBinding
import com.paulik.popularlibraries.domain.entity.ForksRepoGitHubEntity


class ForksRepoAdapter :
    ListAdapter<ForksRepoGitHubEntity, ForksRepoAdapter.ForksRepoViewHolder>(ForksRepoGitHibCallback) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ForksRepoViewHolder {
        return ForksRepoViewHolder(
            ItemGitForksBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        )
    }

    override fun onBindViewHolder(holder: ForksRepoViewHolder, position: Int) {
        holder.showForks(currentList[position])
    }

    inner class ForksRepoViewHolder(private val binding: ItemGitForksBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun showForks(forks: ForksRepoGitHubEntity) {
            binding.nameForksTextView.text = forks.fullName
        }
    }
}

object ForksRepoGitHibCallback : DiffUtil.ItemCallback<ForksRepoGitHubEntity>() {
    // совпадают ли элементы
    override fun areItemsTheSame(
        oldItem: ForksRepoGitHubEntity,
        newItem: ForksRepoGitHubEntity
    ): Boolean {
        return oldItem == newItem
    }

    // На сколько элементы совпадают
    override fun areContentsTheSame(
        oldItem: ForksRepoGitHubEntity,
        newItem: ForksRepoGitHubEntity
    ): Boolean {
        return oldItem == newItem
    }

}