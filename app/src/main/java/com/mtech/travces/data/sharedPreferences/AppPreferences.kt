package com.mtech.travces.data.sharedPreferences

import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import com.mtech.travces.data.remote.travces.TravcesRetroFitClientInstance
import com.mtech.travces.data.remote.travces.model.data.LoginData
import com.mtech.travces.view.activities.LandingActivity
import com.mtech.travces.view.activities.base.BaseActivity


class AppPreferences(context: Context) {
    var context: Context? = context
    var pref: SharedPreferences

    init {
        pref = context.getSharedPreferences(PREF_NAME, Context.MODE_PRIVATE)
    }

    fun isLoggedIn(): Boolean = getAuthenticationToken().isNotEmpty()

    fun setAuthenticationToken(token: String) {
        with(pref.edit()) {
            putString(TOKEN, token)
            apply()
        }
        TravcesRetroFitClientInstance.getInstance(context!!)!!.initRetrofit()
    }

    fun getAuthenticationToken(): String {
        return pref.getString(TOKEN, "")!!
    }

    private fun clearAppPreferences() {
        with(pref.edit()) {
            clear().apply()
        }
    }

    fun logout() {
        clearAppPreferences()
    }

    fun redirectToLogin(context: Context, message: String) {
        val intent = Intent(context, LandingActivity::class.java)
//        intent.putExtra(LandingActivity.SKIP_SPLASH, true)
        intent.putExtra(LandingActivity.START_UP_MESSAGE, message)
        intent.flags = (Intent.FLAG_ACTIVITY_CLEAR_TASK
                or Intent.FLAG_ACTIVITY_CLEAR_TOP
                or Intent.FLAG_ACTIVITY_NEW_TASK)
        context.startActivity(intent)
        (context as BaseActivity).finishAffinity()
    }

    fun setUser(loginData: LoginData) {
        setUserId(loginData.user.id)
        setUserName(loginData.user.name)
        setUserFirstName(loginData.user.first_name)
        setUserLastName(loginData.user.last_name)
        setUserPhone(loginData.user.phone)
        setUserEmail(loginData.user.email)
        setUserProfileImage(loginData.user.profile_pic)
    }

    fun getUser(): LoginData {
        val loginData = LoginData()

        loginData.user.name

        loginData.user.name = getUserName().toString()
        loginData.user.first_name = getUserFirstName().toString()
        loginData.user.last_name = getUserLastName().toString()
        loginData.user.phone = getUserPhone().toString()
        loginData.user.email = getUserEmail().toString()
        loginData.user.id = getUserId()
        loginData.user.profile_pic = getUserProfileImage()

        return loginData
    }

    fun setFcmToken(token: String) {
        with(pref.edit()) {
            putString(KEY_FCM_TOKEN, token)
            apply()
        }
    }

    fun getFcmToken(): String {
        return pref.getString(KEY_FCM_TOKEN, "")!!
    }

    private fun setUserType(user_type: String?) {
        with(pref.edit()) {
            putString(KEY_USER_TYPE, user_type)
            apply()
        }
    }

    private fun getUserType(): String? {
        return pref.getString(KEY_USER_TYPE, "")
    }

    private fun setUserProfileImage(profile_image: String?) {
        with(pref.edit()) {
            putString(KEY_USER_PROFILE_IMAGE, profile_image)
            apply()
        }
    }

    private fun getUserProfileImage(): String? {
        return pref.getString(KEY_USER_PROFILE_IMAGE, "")
    }

    fun setUserEmail(email: String) {
        with(pref.edit()) {
            putString(KEY_USER_EMAIL, email)
            apply()
        }
    }

    private fun getUserEmail(): String? {
        return pref.getString(KEY_USER_EMAIL, "")
    }

    private fun setUserName(name: String) {
        with(pref.edit()) {
            putString(KEY_USER_NAME, name)
            apply()
        }
    }

    private fun getUserName(): String? {
        return pref.getString(KEY_USER_NAME, "")
    }

    fun setUserFirstName(name: String) {
        with(pref.edit()) {
            putString(KEY_USER_FIRST_NAME, name)
            apply()
        }
    }

    private fun getUserFirstName(): String? {
        return pref.getString(KEY_USER_FIRST_NAME, "")
    }

    fun setUserLastName(name: String) {
        with(pref.edit()) {
            putString(KEY_USER_LAST_NAME, name)
            apply()
        }
    }

    private fun getUserLastName(): String? {
        return pref.getString(KEY_USER_LAST_NAME, "")
    }

    fun setUserPhone(phone: String) {
        with(pref.edit()) {
            putString(KEY_USER_PHONE, phone)
            apply()
        }
    }

    private fun getUserPhone(): String? {
        return pref.getString(KEY_USER_PHONE, "")
    }

    private fun setUserId(id: Int) {
        with(pref.edit()) {
            putInt(KEY_USER_ID, id)
            apply()
        }
    }

    private fun getUserId(): Int {
        return pref.getInt(KEY_USER_ID, -1)
    }

    fun setLocale(locale: String) {
        with(pref.edit()) {
            putString(KEY_LOCALE, locale)
            apply()
        }
    }

    fun getLocale(): String {
        return pref.getString(KEY_LOCALE, "en")!!
    }

    fun setNotifications(flag: Boolean) {
        with(pref.edit()) {
            putBoolean(KEY_NOTIFICATIONS, flag)
            apply()
        }
    }

    fun getNotifications(): Boolean {
        return pref.getBoolean(KEY_NOTIFICATIONS, true)
    }

    companion object {

        const val PREF_NAME: String = "app_pref"
        const val TOKEN: String = "authentication_token"
        const val KEY_FCM_TOKEN = "key_fcm_token"

        const val KEY_USER_ID: String = "user_id"
        const val KEY_USER_NAME = "name"
        const val KEY_USER_FIRST_NAME = "firstName"
        const val KEY_USER_LAST_NAME = "lastName"
        const val KEY_USER_EMAIL = "email"
        const val KEY_USER_PROFILE_IMAGE = "profile_pic"
        const val KEY_USER_PHONE = "phone"
        const val KEY_USER_TYPE = "user_type"

        const val KEY_LOCALE = "locale"
        const val KEY_NOTIFICATIONS = "notifications"
    }
}