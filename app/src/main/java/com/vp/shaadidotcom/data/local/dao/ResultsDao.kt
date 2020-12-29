package com.vp.shaadidotcom.data.local.dao

import androidx.room.*
import com.vp.shaadidotcom.data.local.entity.Results
import io.reactivex.Single

/**
 * Created by Vishwanath Patil on 23/11/20.
 */
@Dao
interface ResultsDao {

    @Insert
    fun insertAll(list: MutableList<Results>): Single<MutableList<Long>>

    @Query("SELECT * FROM users")
    fun getAllUsers(): Single<MutableList<Results>>

    @Update
    fun updateStatus(results: Results): Single<Int>

    @Query("SELECT COUNT(*) FROM users")
    fun getCount() : Single<Int>

    @Delete
    fun deleteUser(user: Results) : Single<Int>
}