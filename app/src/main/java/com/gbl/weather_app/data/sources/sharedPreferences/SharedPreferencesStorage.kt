package com.gbl.weather_app.data.sources.sharedPreferences

import android.content.Context

class SharedPreferencesStorage(
    context: Context
) {
    private val sharedPreferences =
        context.getSharedPreferences(PREFERENCE_FILE_KEY, Context.MODE_PRIVATE)

    fun getInitial(): String = sharedPreferences.getString(INITIAL_SEARCH_QUERY_KEY, "") ?: ""

    fun setInitial(searchQuery: String) {
        sharedPreferences.edit().putString(INITIAL_SEARCH_QUERY_KEY, searchQuery).apply()
    }

    companion object {
        private const val PREFERENCE_FILE_KEY = "preferences"
        private const val INITIAL_SEARCH_QUERY_KEY = "initial_search_query"
    }
}