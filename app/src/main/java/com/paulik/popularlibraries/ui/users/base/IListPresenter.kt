package com.paulik.popularlibraries.ui.users.base

interface IListPresenter<V : IItemView> {

    var itemClickListener: () -> Unit

    fun getCount(): Int

    fun bindView(view: V)
}