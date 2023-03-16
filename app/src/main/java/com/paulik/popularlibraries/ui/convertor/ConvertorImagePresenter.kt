package com.paulik.popularlibraries.ui.convertor

import android.annotation.SuppressLint
import android.content.Context
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.widget.Toast
import com.paulik.popularlibraries.domain.ConverterPresenter
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.core.Observable
import io.reactivex.rxjava3.schedulers.Schedulers
import moxy.MvpPresenter
import java.io.FileOutputStream

class ConvertorImagePresenter : MvpPresenter<ConverterPresenter>() {


    @SuppressLint("CheckResult")
    fun onConvertSaveClicked(context: Context, jpgPath: String?) {
        Observable.fromCallable {
            try {
                val bitmap: Bitmap = BitmapFactory.decodeFile(jpgPath)
                val out = FileOutputStream(jpgPath + ".png")
                bitmap.compress(Bitmap.CompressFormat.PNG, 100, out) //100-best quality
                out.close()
            } catch (e: Exception) {
                e.printStackTrace()
            }
        }
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe {
                Toast.makeText(context, "Файл конвертирован в png", Toast.LENGTH_SHORT)
                    .show()
            }


    }
}