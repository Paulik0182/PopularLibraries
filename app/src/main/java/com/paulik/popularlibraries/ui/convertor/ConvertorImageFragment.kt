package com.paulik.popularlibraries.ui.convertor

import android.Manifest
import android.annotation.SuppressLint
import android.app.AlertDialog
import android.content.Intent
import android.content.pm.PackageManager
import android.os.Bundle
import android.provider.MediaStore
import android.view.View
import android.widget.ImageView
import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.contract.ActivityResultContracts
import androidx.core.content.ContextCompat
import com.paulik.popularlibraries.R
import com.paulik.popularlibraries.databinding.FragmentConvertorImageBinding
import com.paulik.popularlibraries.ui.root.ViewBindingFragment
import com.squareup.picasso.Picasso
import moxy.presenter.InjectPresenter
import moxy.presenter.ProvidePresenter
import java.io.File


const val JPG_CHOOSER_CODE = 0

class ConvertorImageFragment : ViewBindingFragment<FragmentConvertorImageBinding>(
    FragmentConvertorImageBinding::inflate
), ConverterMvpView {

    private lateinit var file: File
    private var jpgPath: String? = null

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

    @SuppressLint("CheckResult")
    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)

        val url = data?.data

        requireActivity().contentResolver.query(
            url!!,
            null,
            null,
            null,
            null
        )?.use { cursor ->
            while (cursor.moveToNext()) {
                val absolutePathOfImage =
                    cursor.getString(cursor.getColumnIndexOrThrow(MediaStore.MediaColumns.DATA));
                file = File(absolutePathOfImage)

                jpgPath = absolutePathOfImage

                Picasso.get()
                    .load(file)
                    .placeholder(R.drawable.uploading_images)
                    .into(binding.photoImageView)
                binding.photoImageView.scaleType =
                    ImageView.ScaleType.FIT_CENTER
            }
        }

        binding.nameImageTextView.text = file.name

        binding.converterSaveImageButton.setOnClickListener {
            presenter.onConvertSaveClicked(requireActivity(), jpgPath)
            binding.nameImageTextView.text = jpgPath
        }
    }

    private fun initButton() {
        binding.openImageButton.setOnClickListener {
            checkPermission()
        }
    }

    private fun checkPermission() {
        context?.let {
            when {
                // Внимательно импортировать Manifest (в библиотеках android)
                ContextCompat.checkSelfPermission(it, Manifest.permission.READ_EXTERNAL_STORAGE) ==
                        PackageManager.PERMISSION_GRANTED -> {
                    // Доступ к файлам
                    showFileChooser()
                }
                // пояснение перед запросом разрешения (не обязательно. показывается в окне запроса)
                shouldShowRequestPermissionRationale(Manifest.permission.READ_EXTERNAL_STORAGE) -> {
                    AlertDialog.Builder(it)
                        .setTitle("Доступ к файлам")
                        .setMessage("Для работы с файлами")
                        .setPositiveButton("Предоставить доступ") { _, _ ->
                            // Запрашиваем разрешение
                            requestPermissionLauncher.launch(Manifest.permission.READ_EXTERNAL_STORAGE)
                        }
                        .setNegativeButton("Отказать в доступе") { dialog, _ ->
                            dialog.dismiss()
                        }
                        .create()
                        .show()
                }
                else -> {
                    // если ничего не произошло, то запрашиваем разрешения
                    requestPermissionLauncher.launch(Manifest.permission.READ_EXTERNAL_STORAGE)
                }
            }
        }
    }

    private fun showFileChooser() {
        val intent = Intent(Intent.ACTION_PICK)
        intent.type = "image/jpeg"
        startActivityForResult(intent, JPG_CHOOSER_CODE)
    }

    private val requestPermissionLauncher: ActivityResultLauncher<String> =
        registerForActivityResult(ActivityResultContracts.RequestPermission()) { isGranted ->
            if (isGranted) {
                // пермиссия выдана
                showFileChooser()
            } else {
                // не выдана
                context?.let {
                    AlertDialog.Builder(it)
                        .setMessage("Уведомление")
                        .setTitle("Доступ не разрешен!")
                        .setNegativeButton("Закрыть") { dialog, _ ->
                            dialog.dismiss()
                        }.show()
                }
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