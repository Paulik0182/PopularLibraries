package com.paulik.popularlibraries.ui.users.details

import android.os.Bundle
import com.paulik.popularlibraries.databinding.FragmentDetailsUserGitHubBinding
import com.paulik.popularlibraries.ui.root.ViewBindingFragment

class DetailsUserGitHubFragment : ViewBindingFragment<FragmentDetailsUserGitHubBinding>(
    FragmentDetailsUserGitHubBinding::inflate
) {

    companion object {

        @JvmStatic
        fun newInstance() =
            DetailsUserGitHubFragment().apply {
                arguments = Bundle().apply {

                }
            }
    }
}