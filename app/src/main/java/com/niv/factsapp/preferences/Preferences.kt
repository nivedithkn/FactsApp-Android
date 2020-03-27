package com.niv.factsapp.preferences

import android.content.Context
import android.content.SharedPreferences

/**
 * Preferences - A utility class for preference
 *
 * @author Nivedith
 * @since 2020-03-27.
 */

object PreferenceHelper {

    var factsListItem: String?
        get() = get("factsListItem", "")
        set(value) = put("factsListItem", value)

    private var prefs: SharedPreferences? = null

    fun initWith(context: Context) {
        if (prefs == null) this.prefs =
            context.getSharedPreferences("factsapp_prefs", Context.MODE_PRIVATE)
    }

    fun clearAll() {
        if (prefs == null) throw RuntimeException("Initialize PreferenceHelper first with #initWith(context).")
        prefs?.edit()?.clear()?.apply()
    }

    private inline fun <reified T> get(key: String, defaultValue: T): T {

        if (prefs == null) throw RuntimeException("Initialize PreferenceHelper first with #initWith(context).")

        return when (T::class) {
            Boolean::class -> prefs?.getBoolean(key, defaultValue as Boolean) as T
            Float::class -> prefs?.getFloat(key, defaultValue as Float) as T
            Int::class -> prefs?.getInt(key, defaultValue as Int) as T
            Long::class -> prefs?.getLong(key, defaultValue as Long) as T
            String::class -> prefs?.getString(key, defaultValue as String) as T
            else -> defaultValue
        }
    }

    private inline fun <reified T> put(key: String, value: T) {

        if (prefs == null) throw RuntimeException("Initialize PreferenceHelper first with #initWith(context).")

        val editor = prefs?.edit()

        when (T::class) {
            Boolean::class -> editor?.putBoolean(key, value as Boolean)
            Float::class -> editor?.putFloat(key, value as Float)
            Int::class -> editor?.putInt(key, value as Int)
            Long::class -> editor?.putLong(key, value as Long)
            String::class -> editor?.putString(key, value as String)
        }

        editor?.apply()
    }

}