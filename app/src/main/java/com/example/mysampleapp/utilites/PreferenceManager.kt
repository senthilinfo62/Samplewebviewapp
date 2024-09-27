package com.example.mysampleapp.utilites

import android.content.Context
import android.content.SharedPreferences

object PreferenceManager {

    private const val PREFS_NAME = "my_app_prefs"
    private const val KEY_FIRST_LAUNCH = "first_launch"

    fun isFirstLaunch(context: Context): Boolean {
        val preferences: SharedPreferences = context.getSharedPreferences(PREFS_NAME, Context.MODE_PRIVATE)
        return preferences.getBoolean(KEY_FIRST_LAUNCH, true) // Default is true for first-time launch
    }

    fun setFirstLaunch(context: Context, isFirstLaunch: Boolean) {
        val preferences: SharedPreferences = context.getSharedPreferences(PREFS_NAME, Context.MODE_PRIVATE)
        val editor = preferences.edit()
        editor.putBoolean(KEY_FIRST_LAUNCH, isFirstLaunch)
        editor.apply()
    }
}