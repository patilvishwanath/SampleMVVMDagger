package com.vp.shaadidotcom.data.local.dao

import androidx.room.*
import com.vp.shaadidotcom.data.local.entity.Results
import com.vp.shaadidotcom.data.local.entity.User
import io.reactivex.Single

/**
 * Created by Vishwanath Patil on 08/10/20.
 */
@Dao
interface UserDao {

    @Insert
    fun insertAll(list: List<Results>): Single<List<Long>>

    @Query("SELECT * FROM users")
    fun getAllUsers(): Single<List<Results>>

    @Update
    fun updateStatus(results: Results): Single<Int>

    @Query("SELECT COUNT(*) FROM users")
    fun getCount() : Single<Int>

    @Delete
    fun deleteUser(user: Results) : Single<Int>



    //------------------

    @Insert
    fun insertAllUser(list: List<User>): Single<List<Long>>

    @Update
    fun updateStatus(user: User): Single<Int>

    @Query("SELECT * FROM users_db")
    fun getAllUser(): Single<List<User>>


    @Query("SELECT COUNT(*) FROM users_db")
    fun getCountUser() : Single<Int>

    @Delete
    fun deleteUser(user: User) : Single<Int>


}