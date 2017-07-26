package com.kryx07.moneyleft

import android.content.Context
import android.content.SharedPreferences
import android.util.Log
import timber.log.Timber


class SharedPreferencesManager(val context: Context) {

    private val editor: SharedPreferences.Editor = context.getSharedPreferences(context.getString(R.string.shared_prefs), 0).edit()
    private val sharedPreferences: SharedPreferences = context.getSharedPreferences(context.getString(R.string.shared_prefs), Context.MODE_PRIVATE)

    fun write(key: String, string: String) {

        editor.putString(key, string)
        editor.apply()

        val savedValue = sharedPreferences.getString(key,
                context.getString(R.string.no_value))
        Timber.e("Saved ", savedValue + " under key: " + key)
    }

    fun write(key: Int, string: String) {
        write(context.getString(key), string)
    }

    fun read(key: String): String {
        val defVal = context.getString(R.string.no_value)
        return sharedPreferences.getString(key, defVal)

    }

    fun read(key: Int): String {
        return read(context.getString(key))
    }


}