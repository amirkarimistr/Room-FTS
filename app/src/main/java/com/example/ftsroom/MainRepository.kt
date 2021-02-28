package com.example.ftsroom

import android.app.Application
import androidx.room.Room
import androidx.room.migration.Migration
import androidx.sqlite.db.SupportSQLiteDatabase

class MainRepository(application: Application) {

    val db = Room.databaseBuilder(
        application.applicationContext,
        AppDatabase::class.java, "database-name"
    ).allowMainThreadQueries()
        .addMigrations(object: Migration(3,4){
            override fun migrate(database: SupportSQLiteDatabase) {
                database.execSQL("CREATE VIRTUAL TABLE IF NOT EXISTS `user_fts` USING FTS4(`first_name`, `last_name`, content=`user`)");
                database.execSQL("INSERT INTO user_fts(user_fts) VALUES ('rebuild')");
            }
        })
        .fallbackToDestructiveMigration()
        .build()

    val dao = db.userDao()

    suspend fun getData() = dao.getAll()

    suspend fun getFilteredData(term: String) = dao.getFilterData(term)

    suspend fun insertUsers(vararg users: User) = dao.insertAll(*users)
}