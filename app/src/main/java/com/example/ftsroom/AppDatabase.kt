package com.example.ftsroom

import androidx.room.Database
import androidx.room.RoomDatabase

@Database(entities = arrayOf(User::class, UserFts::class), version = 5, exportSchema = false)
abstract class AppDatabase : RoomDatabase() {
    abstract fun userDao(): UserDao
}