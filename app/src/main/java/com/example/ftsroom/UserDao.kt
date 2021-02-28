package com.example.ftsroom

import androidx.room.*

@Dao
interface UserDao {
    @Query("SELECT * FROM user")
    suspend fun getAll(): List<User>

    @Query("SELECT * FROM user WHERE uid IN (:userIds)")
    fun loadAllByIds(userIds: IntArray): List<User>

    @Query(
        "SELECT * FROM user WHERE first_name LIKE :first AND " +
                "last_name LIKE :last LIMIT 1"
    )
    fun findByName(first: String, last: String): User

    @Insert
    suspend fun insertAll(vararg users: User)

    @Delete
    fun delete(user: User)

    @Transaction
    @Query("SELECT user.first_name, user.last_name FROM user "+ "JOIN user_fts ON (user.uid == user_fts.docid) WHERE user_fts MATCH :term")
    suspend fun getFilterData(term: String): List<UserCallback>
}