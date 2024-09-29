package com.gbl.weather_app.data.sources.db

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(
    entities = [WeatherDbModel::class, LocationDbModel::class],
    version = 1,
    exportSchema = false
)
abstract class AppDatabase : RoomDatabase() {

    abstract fun locationsDao(): LocationDao

    abstract fun forecastDao(): ForecastDao

    companion object {
        private const val DB_NAME = "db"

        private var instance: AppDatabase? = null

        fun getInstance(context: Context): AppDatabase = instance ?: synchronized(this) {
            Room.databaseBuilder(
                context,
                AppDatabase::class.java,
                DB_NAME
            ).build().also { instance = it }
        }
    }
}