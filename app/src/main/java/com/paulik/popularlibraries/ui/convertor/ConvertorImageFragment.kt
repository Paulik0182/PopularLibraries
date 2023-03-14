package com.paulik.popularlibraries.ui.convertor

import android.os.Bundle
import android.view.View
import android.widget.Toast
import com.paulik.popularlibraries.databinding.FragmentConvertorImageBinding
import com.paulik.popularlibraries.domain.ConverterPresenter
import com.paulik.popularlibraries.domain.entity.ImageEntity
import com.paulik.popularlibraries.ui.root.ViewBindingFragment
import moxy.presenter.InjectPresenter
import moxy.presenter.ProvidePresenter

class ConvertorImageFragment : ViewBindingFragment<FragmentConvertorImageBinding>(
    FragmentConvertorImageBinding::inflate
), ConverterPresenter {

    private val entity = ImageEntity(image = "нет")

    @InjectPresenter
    lateinit var presenter: ConvertorImagePresenter

    @ProvidePresenter
    fun providePresenter(): ConvertorImagePresenter {
        return ConvertorImagePresenter()
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        initButton()
    }

    private fun initButton() {

        val nameImage = binding.nameImageEditText.setText(entity.image).toString()
//        val reader =  requireContext().assets.open(nameImage)
//        val image = resources.getIdentifier(reader, "assets/jpg", "assets/jpg")

//        val url = requireContext().assets.open("/jpg/$nameImage")

        binding.openImageButton.setOnClickListener {
            Toast.makeText(requireContext(), nameImage, Toast.LENGTH_SHORT).show()
//            binding.photoImageView.setImageResource(image)
//            Picasso.get()
//                .load(url)
//                .placeholder(R.drawable.uploading_images)
//                .into(binding.photoImageView)
//            binding.photoImageView.scaleType =
//                ImageView.ScaleType.FIT_CENTER
        }
    }

    companion object {

        @JvmStatic
        fun newInstance() =
            ConvertorImageFragment().apply {
                arguments = Bundle().apply {

                }
            }
    }

    override fun getImage() {
        //TODO("Not yet implemented")
    }

    override fun saveImage() {
        //TODO("Not yet implemented")
    }
}