package com.vp.shaadidotcom.data.local.db

import androidx.room.Database
import androidx.room.RoomDatabase
import com.vp.shaadidotcom.data.local.dao.UserDao
import com.vp.shaadidotcom.data.local.dao.AddressDao
import com.vp.shaadidotcom.data.local.dao.ResultsDao
import com.vp.shaadidotcom.data.local.entity.Addresss
import com.vp.shaadidotcom.data.local.entity.Results
import com.vp.shaadidotcom.data.local.entity.User
import javax.inject.Singleton

/**
 * Created by Vishwanath Patil on 08/10/20.
 */
@Singleton
@Database(
        entities = [Results::class],//[User::class,Results::class,Addresss::class],
        exportSchema = false,
        version = 1
)
abstract class DataBaseService : RoomDatabase() {

 //   abstract fun userDao() : UserDao
//
//    abstract fun addressDao() : AddressDao

    abstract fun resultsDao() : ResultsDao



}