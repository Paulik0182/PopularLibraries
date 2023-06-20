package com.paulik.popularlibraries.ui.settings

import android.annotation.SuppressLint
import android.content.Context
import android.os.Bundle
import android.view.View
import com.paulik.popularlibraries.BuildConfig
import com.paulik.popularlibraries.R
import com.paulik.popularlibraries.databinding.FragmentAboutAppBinding
import com.paulik.popularlibraries.ui.root.ViewBindingFragment

class AboutAppFragment : ViewBindingFragment<FragmentAboutAppBinding>(
    FragmentAboutAppBinding::inflate
) {

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        informationApp()
    }

    @SuppressLint("SetTextI18n")
    private fun informationApp() {
        binding.codVersionTextView.text = "Код версии: " + BuildConfig.VERSION_CODE
        binding.versionTextView.text = "Версия: " + BuildConfig.VERSION_NAME
        binding.aboutAppTextView.text = getText(R.string.about_app_text)
    }

    interface Controller {
        //TODO
    }

    private fun getController(): Controller = activity as Controller

    override fun onAttach(context: Context) {
        super.onAttach(context)
        getController()
    }

    companion object {

        @JvmStatic
        fun newInstance() = AboutAppFragment()
    }
}