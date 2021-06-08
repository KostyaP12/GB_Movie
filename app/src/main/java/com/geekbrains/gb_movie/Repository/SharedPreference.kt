package com.geekbrains.gb_movie.Repository

import android.app.Activity
import android.content.SharedPreferences

class SharedPreference(activity: Activity) {
    private val sPreferences: SharedPreferences = activity.getPreferences(Activity.MODE_PRIVATE)
    var _editor: SharedPreferences.Editor? = null
    val editor: SharedPreferences.Editor
        get() = sPreferences.edit()

    fun adultBoolean(tag: String?, value: Boolean) {
        _editor = editor
        _editor?.putBoolean(tag, value)
        _editor?.commit()
    }
}