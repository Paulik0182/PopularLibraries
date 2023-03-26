package com.paulik.popularlibraries.ui.users.base

import androidx.core.os.bundleOf
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentFactory

/**
 * Используем при открытии фрагмента и передачи в него параметров
 */
abstract class FragmentInitializer<T> {

    fun newInstance(initParams: T): Fragment {
        val declaredClassName = this::class.java.declaringClass?.name.orEmpty()
        val fragment = FragmentFactory().instantiate(
            this::class.java.declaringClass?.classLoader!!,
            declaredClassName
        )
        return fragment.apply {
            arguments = bundleOf(
                declaredClassName to initParams
            )
        }
    }
}

// Обработка полученных данных (то что раньше было константой)
fun <T> Fragment.initParams(): Lazy<T> = lazy {
    requireArguments().getString(this::class.java.name) as T
}