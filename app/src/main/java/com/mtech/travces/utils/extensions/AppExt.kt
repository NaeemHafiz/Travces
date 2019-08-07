package com.mtech.travces.utils.extensions

import android.content.Context
import android.text.TextUtils
import android.util.Patterns
import android.view.View
import android.view.inputmethod.InputMethodManager
import android.widget.ImageView
import androidx.fragment.app.Fragment
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.bumptech.glide.request.RequestOptions
import com.esafirm.imagepicker.features.ImagePicker
import com.mtech.travces.R
import java.text.SimpleDateFormat
import java.util.*

fun isEmailValid(target: CharSequence): Boolean {
    return !TextUtils.isEmpty(target) && Patterns.EMAIL_ADDRESS.matcher(target).matches()
}

fun isPhoneValid(phoneNumber: String): Boolean {
    return (!TextUtils.isEmpty(phoneNumber.trim { it <= ' ' })
            && Patterns.PHONE.matcher(phoneNumber.trim { it <= ' ' }).matches()
            && phoneNumber.length > 10)
}

fun loadProfileImage(imageUrl: String, imageView: ImageView) {
    Glide.with(imageView.context).load(imageUrl)
        .apply(RequestOptions().centerCrop().circleCrop().diskCacheStrategy(DiskCacheStrategy.AUTOMATIC))
        .into(imageView)
}

fun allFieldsFilled(vararg fields: String): Boolean {

    fields.forEach {
        if (it.isEmpty())
            return false
    }

    return true
}

fun anyFieldsFilled(vararg fields: String): Boolean {

    fields.forEach {
        if (it.isNotEmpty())
            return true
    }

    return false
}

fun showImagePicker(fragment: Fragment) {

    ImagePicker.create(fragment)
        .limit(1)
        .theme(R.style.AppTheme)
        .includeVideo(false)
        .showCamera(true)
        .start()
}

fun hideKeyboard(view: View) {
    try {
        val imm = view.context.getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
        imm.hideSoftInputFromWindow(view.windowToken, 0)
    } catch (e: NullPointerException) {
        e.printStackTrace()
    }
}

fun showKeyboard(view: View) {
    try {
        val imm = view.context.getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
        imm.showSoftInput(view, 0)
    } catch (e: NullPointerException) {
        e.printStackTrace()
    }

}


fun getFormatedDateTime(
    dateStr: String,
    strReadFormat: String = "HH:mm:ss",
    strWriteFormat: String = "hh:mm a"
): String {

    var formattedDate = dateStr

    val readFormat = SimpleDateFormat(strReadFormat, Locale.getDefault())
    val writeFormat = SimpleDateFormat(strWriteFormat, Locale.getDefault())

    var date: Date? = null

    try {
        date = readFormat.parse(dateStr)
    } catch (e: Exception) {
    }

    if (date != null) {
        formattedDate = writeFormat.format(date)
    }

    return formattedDate
}

fun getRequestCode(): Int {
    val rnd = Random()
    return 100 + rnd.nextInt(900000)
}