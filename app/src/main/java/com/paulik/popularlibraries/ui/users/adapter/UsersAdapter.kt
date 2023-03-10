package com.paulik.popularlibraries.ui.users.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.paulik.popularlibraries.databinding.ItemUsersBinding
import com.paulik.popularlibraries.domain.entity.UsersGitHubEntity


class UsersAdapter(
    private val itemClickListener: (UsersGitHubEntity) -> Unit
) : ListAdapter<UsersGitHubEntity, UsersAdapter.UserViewHolder>(GitHibUserCallback) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): UserViewHolder {
        return UserViewHolder(
            ItemUsersBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        )
    }

    override fun onBindViewHolder(holder: UserViewHolder, position: Int) {
        holder.showUser(currentList[position])
    }

    inner class UserViewHolder(private val binding: ItemUsersBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun showUser(user: UsersGitHubEntity) {
            itemView.setOnClickListener { itemClickListener(user) }
//            binding.root.setOnClickListener { itemClickListener(user) } // выриант записи

            binding.loginTextView.text = user.login
        }
    }
}

object GitHibUserCallback : DiffUtil.ItemCallback<UsersGitHubEntity>() {
    // совпадают ли элементы
    override fun areItemsTheSame(oldItem: UsersGitHubEntity, newItem: UsersGitHubEntity): Boolean {
        return oldItem == newItem
    }

    // На сколько элементы совпадают
    override fun areContentsTheSame(
        oldItem: UsersGitHubEntity,
        newItem: UsersGitHubEntity
    ): Boolean {
        return oldItem == newItem
    }

}