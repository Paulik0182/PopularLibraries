package com.paulik.popularlibraries.ui.users.base

import androidx.core.os.bundleOf
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentFactory
import java.io.Serializable

/**
 * Используем при открытии фрагмента и передачи в него параметров
 */

interface InitParams : Serializable
abstract class FragmentInitializer<T : InitParams> {

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
@Suppress("UNCHECKED_CAST")
fun <T : InitParams> Fragment.initParams(): Lazy<T> = lazy {
    requireArguments().getString(this::class.java.name) as T
}