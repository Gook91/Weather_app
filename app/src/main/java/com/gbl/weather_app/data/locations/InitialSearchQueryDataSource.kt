package com.gbl.weather_app.data.locations

import com.gbl.weather_app.data.sources.sharedPreferences.SharedPreferencesStorage
import javax.inject.Inject

class InitialSearchQueryDataSource @Inject constructor(
   private val sharedPreferencesStorage: SharedPreferencesStorage
) {
    fun getInitial(): String = sharedPreferencesStorage.getInitial()

    fun setInitial(searchQuery: String) = sharedPreferencesStorage.setInitial(searchQuery)
}