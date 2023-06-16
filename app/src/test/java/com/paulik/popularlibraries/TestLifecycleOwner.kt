package com.paulik.popularlibraries

import android.annotation.SuppressLint
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.LifecycleRegistry
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.runBlocking

/**
 * Create a [LifecycleOwner] that allows changing the state via the
 * [handleLifecycleEvent] method or [currentState] property.
 *
 * Under the hood, this uses a [LifecycleRegistry]. However, it uses
 * [Dispatchers.Main.immediate][kotlinx.coroutines.MainCoroutineDispatcher.immediate] as the
 * default [coroutineDispatcher] to ensure that all
 * mutations to the [current state][currentState] are run on that dispatcher, no
 * matter what thread you mutate the state from.
 *
 * @param initialState The initial [Lifecycle.State].
 */
class TestLifecycleOwner @JvmOverloads constructor(
    initialState: Lifecycle.State = Lifecycle.State.STARTED,
    private val coroutineDispatcher: CoroutineDispatcher = Dispatchers.Main.immediate
) : LifecycleOwner {
    // it is in test artifact
    @SuppressLint("VisibleForTests")
    private val lifecycleRegistry = LifecycleRegistry.createUnsafe(this).apply {
        currentState = initialState
    }

    override fun getLifecycle() = lifecycleRegistry

    /**
     * Update the [currentState] by moving it to the state directly after the given [event].
     * This is safe to call on any thread.
     */
    fun handleLifecycleEvent(event: Lifecycle.Event) {
        runBlocking(coroutineDispatcher) {
            lifecycleRegistry.handleLifecycleEvent(event)
        }
    }

    /**
     * The current [Lifecycle.State] of this owner. This is safe to mutate on any thread.
     */
    var currentState: Lifecycle.State
        get() = runBlocking(coroutineDispatcher) {
            lifecycleRegistry.currentState
        }
        set(value) {
            runBlocking(coroutineDispatcher) {
                lifecycleRegistry.currentState = value
            }
        }

    /**
     * Get the number of observers.
     */
    val observerCount get() = lifecycleRegistry.observerCount
}
