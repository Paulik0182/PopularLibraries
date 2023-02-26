package com.paulik.popularlibraries.ui.root

import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import com.paulik.popularlibraries.R
import com.paulik.popularlibraries.databinding.ActivityRootBinding
import com.paulik.popularlibraries.ui.counter.CounterBasedMvpFragment
import com.paulik.popularlibraries.ui.settings.AboutAppFragment
import com.paulik.popularlibraries.ui.settings.SettingsFragment

private const val TAG_ROOT_CONTAINER_LAYOUT_KEY = "TAG_ROOT_CONTAINER_LAYOUT_KEY"

class RootActivity : AppCompatActivity(),
    SettingsFragment.Controller,
    StartingFragment.Controller,
    AboutAppFragment.Controller,
    CounterBasedMvpFragment.Controller {

    private var _binding: ActivityRootBinding? = null
    private val binding get() = _binding!!

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        _binding = ActivityRootBinding.inflate(layoutInflater)
        setContentView(binding.root)

        onBottomNaviBar()

        if (savedInstanceState == null) {
            binding.bottomNavBar.selectedItemId = R.id.starting_item
        } else {
            // todo другое
        }
    }

    private fun onBottomNaviBar() {
        binding.bottomNavBar.setOnItemSelectedListener {
            when (it.itemId) {
                R.id.starting_item -> {
                    navigateTo(StartingFragment())
                }
                R.id.settings_item -> {
                    navigateTo(SettingsFragment())
                }
                else -> throw IllegalStateException("Такого фрагмента нет")
            }
            return@setOnItemSelectedListener true
        }
    }

    // Анимация перехода между фрагментами
    private fun navigateTo(fragment: Fragment) {
        supportFragmentManager.beginTransaction().setCustomAnimations(
            R.anim.slide_in,
            R.anim.fade_out,
            R.anim.fade_in,
            R.anim.slide_out
        ).replace(
            binding.fragmentContainerFrameLayout.id, fragment,
            TAG_ROOT_CONTAINER_LAYOUT_KEY
        ).commit()
    }

    // Анимация перехода между фрагментами с BackStack
    private fun navigateWithBackStack(fragment: Fragment) {
        supportFragmentManager.beginTransaction().setCustomAnimations(
            R.anim.slide_in,
            R.anim.fade_out,
            R.anim.fade_in,
            R.anim.slide_out
        ).replace(
            binding.fragmentContainerFrameLayout.id, fragment,
            TAG_ROOT_CONTAINER_LAYOUT_KEY
        )
            .addToBackStack(null)
            .commit()
    }

    private fun onAboutApp() {
        navigateWithBackStack(AboutAppFragment.newInstance())
        binding.bottomNavBar.visibility = View.GONE
    }

    private fun onCounterBasedMvp() {
        navigateWithBackStack(CounterBasedMvpFragment())
        binding.bottomNavBar.visibility = View.GONE
    }

    override fun openAboutApp() {
        onAboutApp()
    }

    override fun openCounterBasedMvp() {
        onCounterBasedMvp()
    }

    @Deprecated("Deprecated in Java")
    override fun onBackPressed() {
        binding.bottomNavBar.visibility = View.VISIBLE
        super.onBackPressed()
    }
}