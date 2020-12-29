package com.vp.shaadidotcom.data.repository

import android.content.Context
import android.util.Log
import androidx.lifecycle.LiveData
import com.vp.shaadidotcom.data.local.db.DataBaseService
import com.vp.shaadidotcom.data.local.entity.Addresss
import com.vp.shaadidotcom.data.local.entity.Base
import com.vp.shaadidotcom.data.local.entity.Results
import com.vp.shaadidotcom.data.local.entity.User
import com.vp.shaadidotcom.data.remote.NetworkService
import io.reactivex.Observable
import io.reactivex.Single
import javax.inject.Inject

/**
 * Created by Vishwanath Patil on 08/10/20.
 */
class UserRepo  @Inject constructor(val context: Context, val dataBaseService: DataBaseService,val networkService: NetworkService) {



    //Networking

    fun fetchDataFromApi() : Single<Base>{
        Log.v("UserRepo","fetchDataFromApi()")
       return networkService.getResults("10")
    }



    //DATABASE
    fun getListResults() : Single<MutableList<Results>>{
       return dataBaseService.resultsDao().getAllUsers()
    }

    fun insertListResults(list: MutableList<Results>) : Single<MutableList<Long>>{
        return dataBaseService.resultsDao().insertAll(list)
    }

    fun updateResult(results: Results) : Single<Int>{
        return dataBaseService.resultsDao().updateStatus(results)
    }

    fun getCount() : Single<Int>{
        return dataBaseService.resultsDao().getCount()
    }

    fun deleteUser(results: Results): Single<Int>{
        return dataBaseService.resultsDao().deleteUser(results)
    }



    //------------------------

//
//    fun getListUsers() : Single<List<User>>{
//        return dataBaseService.userDao().getAllUser()
//    }
//
//    fun insertListUsers(list: List<User>) : Single<List<Long>>{
//        return dataBaseService.userDao().insertAllUser(list)
//    }
//
//    fun updateUser(user: User) : Single<Int>{
//        return dataBaseService.userDao().updateStatus(user)
//    }
//
//    fun getCountUser() : Single<Int>{
//        return dataBaseService.userDao().getCountUser()
//    }
//
//    fun deleteUser(user: User): Single<Int>{
//        return dataBaseService.userDao().deleteUser(user)
//    }

    //------------------------

//    fun getListAddress() : Single<List<Addresss>>{
//        return dataBaseService.addressDao().getAllAddress()
//    }
//
//    fun insertListAddress(list: List<Addresss>) : Single<List<Long>>{
//        return dataBaseService.addressDao().insertAllAddress(list)
//    }
//
//
//    fun updateAddress(addresss: Addresss) : Single<Int>{
//        return dataBaseService.addressDao().updateAddress(addresss)
//    }
//
//    fun getCountAddress() : Single<Int>{
//        return dataBaseService.addressDao().getCountAddress()
//    }
//
//    fun deleteAddress(addresss: Addresss) : Single<Int>{
//        return dataBaseService.addressDao().deleteAddress(addresss)
//    }


}