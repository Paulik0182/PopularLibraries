package com.paulik.popularlibraries.ui.users.adapter

import com.paulik.popularlibraries.ui.users.base.IItemView


interface UserItemView : IItemView {

    fun setLogin(login: String)
}