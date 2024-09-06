package com.nicolas.androidofflinefirst.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.nicolas.androidofflinefirst.database.dao.GameDao
import com.nicolas.androidofflinefirst.database.model.GameEntity

@Database(entities = [GameEntity::class], version = 1, exportSchema = false)
abstract class ApplicationDatabase : RoomDatabase() {

    abstract fun gameDao(): GameDao

    companion object {
        fun roomDatabaseBuilder(context: Context) =
            Room.databaseBuilder(
                context,
                ApplicationDatabase::class.java,
                "application_database"
            ).fallbackToDestructiveMigration()
                .build()
    }
}