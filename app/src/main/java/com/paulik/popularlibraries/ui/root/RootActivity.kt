package com.paulik.popularlibraries.ui.root

import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import com.paulik.popularlibraries.R
import com.paulik.popularlibraries.databinding.ActivityRootBinding
import com.paulik.popularlibraries.ui.convertor.ConvertorImageFragment
import com.paulik.popularlibraries.ui.counter.CounterBasedMvpFragment
import com.paulik.popularlibraries.ui.settings.AboutAppFragment
import com.paulik.popularlibraries.ui.settings.SettingsFragment
import com.paulik.popularlibraries.ui.users.UserGitHubMvpActivity
import moxy.MvpAppCompatActivity

private const val TAG_ROOT_CONTAINER_LAYOUT_KEY = "TAG_ROOT_CONTAINER_LAYOUT_KEY"
private const val USERS_GIT_HUB_REQUEST_KOD = 100

class RootActivity : MvpAppCompatActivity(),
    SettingsFragment.Controller,
    StartingFragment.Controller,
    AboutAppFragment.Controller {

    private var _binding: ActivityRootBinding? = null
    private val binding get() = _binding!!

    private val startingFragment: StartingFragment by lazy { StartingFragment() }
    private val settingsFragment: SettingsFragment by lazy { SettingsFragment() }

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
                    navigateTo(startingFragment)
                }
                R.id.settings_item -> {
                    navigateTo(settingsFragment)
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

    private fun onUsersGitHub() {
        val intent = Intent(this, UserGitHubMvpActivity::class.java)
        startActivityForResult(intent, USERS_GIT_HUB_REQUEST_KOD)
        binding.bottomNavBar.visibility = View.GONE
    }

    private fun onConverterImage() {
        navigateWithBackStack(ConvertorImageFragment())
        binding.bottomNavBar.visibility = View.GONE
    }

    override fun openAboutApp() {
        onAboutApp()
    }

    override fun openCounterBasedMvp() {
        onCounterBasedMvp()
    }

    override fun openUsersGitHub() {
        onUsersGitHub()
    }

    override fun openConverterImage() {
        onConverterImage()
    }

    @Deprecated("Deprecated in Java")
    override fun onBackPressed() {
        binding.bottomNavBar.visibility = View.VISIBLE
        super.onBackPressed()
    }
}