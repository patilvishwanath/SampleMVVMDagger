package com.vp.shaadidotcom.ui

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.vp.shaadidotcom.data.local.entity.*
import com.vp.shaadidotcom.data.repository.UserRepo
import io.reactivex.Single
import io.reactivex.SingleObserver
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.functions.Consumer
import io.reactivex.functions.Function
import io.reactivex.schedulers.Schedulers
import java.util.*
import javax.inject.Inject

//.map {
//    if(it.results.isNotEmpty()){
//
//        userRepo.insertListResults(it.results)
//                .flatMap{resultId ->
//                    userRepo.getListResults()
//
//                }
//
//    }else{
//        Log.v(DashboardViewModel.TAG,"no data")
//    }
//}

/**
 * Created by Vishwanath Patil on 08/10/20.
 */
class DashboardViewModel @Inject constructor(val userRepo: UserRepo, val compositeDisposable: CompositeDisposable) : ViewModel() {

    val listData = MutableLiveData<List<User>>()
    val resultData = MutableLiveData<MutableList<Results>>()
    val addressData = MutableLiveData<List<Addresss>>()
    val deletedData = MutableLiveData<Int>()


    private val _progressData = MutableLiveData<Boolean>()
    val progressData: LiveData<Boolean>
        get() = _progressData

    val list = mutableListOf<User>()
    val addressList = mutableListOf<Addresss>()
    val resultList = mutableListOf<Results>()

    companion object {
        const val TAG = "DashboardViewModel"
    }


    init {


        _progressData.postValue( true)
        compositeDisposable.add(

                userRepo.getCount()
                        .flatMap {
                            if (it == 0)
                                userRepo
                                        .fetchDataFromApi()
                                        .flatMap { entireResult ->

                                            userRepo
                                                    .insertListResults(entireResult.results)
                                                    .flatMap { listResult ->
                                                        userRepo
                                                                .getListResults()
                                                    }

                                        }
                            else {
                                userRepo.getListResults()
                            }
                        }

                        .subscribeOn(Schedulers.io())
                        .subscribe(
                                {
                                    Log.v(TAG, it.size.toString())
                                    Log.v(TAG, it.toString())
                                    resultData.postValue(it)

                                    _progressData.postValue( false)
                                },
                                {

                                    _progressData.postValue( false)

                                })


        )
    }


//    init {
//
//        list.add(User(name = Namee(first = "Vishu",last = "patil"), status = 0))
//        list.add(User(name = Namee(first = "kedar",last = "patil"), status = 1))
//        list.add(User(name = Namee(first = "Ashok",last = "patil"), status = 1))
//        list.add(User(name =Namee(first = "Sushila",last = "patil"), status = 0))


//
//        list.add(Results(name = Name(first = "V",last = "P"),email = "vp@gmail.com",gender = "Male",cell = "1234567890"))
//
//        list.add(Results(name = Name(first = "k",last = "P"),email = "kp@gmail.com",gender = "Male",cell = "1234567890"))
//
//
//        list.add(Results(name = Name(first = "a",last = "P"),email = "ap@gmail.com",gender = "Male",cell = "1234567890"))
//
//
//        list.add(Results(name = Name(first = "s",last = "P"),email = "kp@gmail.com",gender = "female",cell = "1234567890"))
//
//
//        list.add(Results(name = Name(first = "SAR",last = "P"),email = "sarp@gmail.com",gender = "female",cell = "1234567890"))
//        list.add(Results(name = Name(first = "Bas",last = "P"),email = "basp@gmail.com",gender = "Male",cell = "1234567890"))
//
//        list.add(Results(name = Name(first = "Anna",last = "P"),email = "annap@gmail.com",gender = "Male",cell = "1234567890"))
//


//        compositeDisposable.add(
//               userRepo.getCountUser()
//                       .flatMap { if(it == 0){
//
//                           userRepo.insertListUsers(list)
//                                   .flatMap { addressData ->
//                                       userRepo.insertListAddress(
//                                               getAddessListData(addressData)
//                                       )
//                                   }
//
//
//                       }else{
//                           Single.just(0)
//                       }
//                       }
//                       .subscribeOn(Schedulers.io())
//                       .subscribe({
//                           Log.v(TAG,"success ${it.toString()}")
//                       },{
//                           Log.v(TAG,"error ${it.toString()}")
//                       })
//       )


//        compositeDisposable.add(
//                userRepo.getCount()
//                        .flatMap { if(it == 0){
//                            userRepo.insertListResults(list)
//                        }else {
//                            Single.just(0)
//                        } }
//                        .subscribeOn(Schedulers.io())
//                        .subscribe({
//                            Log.v(TAG,it.toString())
//                        },{
//                            Log.v(TAG,it.toString())
//                        })
//
//
////        )
//
//    }

    private fun getAddessListData(addressData: List<Long>): List<Addresss> {
        val cities = listOf("Mumbai", "Delhi", "Chennai", "Pune", "Banglore")
        val hashMapPin = hashMapOf(1 to "421605",
                2 to "421605",
                3 to "421605",
                4 to "421605", 5 to "421605", 6 to "421605"
        )
        val hashMapCities = hashMapOf(1 to "Mumbai",
                2 to "Delhi",
                3 to "Chennai",
                4 to "Pune", 5 to "Banglore", 6 to "Kolkata"
        )


        for (i in 1 until addressData.size) {
            addressList.add(Addresss(city = hashMapCities[i]!!, pincode = hashMapPin[i]!!, userId = addressData[i].toString()))
        }


        return addressList

    }
//
//    fun getUsers(){
//        compositeDisposable.add(
//                userRepo.getListUsers()
//                        .subscribeOn(Schedulers.io())
//                        .subscribe({
//                            listData.postValue(it)
//                            Log.v(TAG,it.toString())
//                        },{
//                            Log.v(TAG,it.toString())
//                        })
//
//        )
//
//
////        compositeDisposable.add(
////                userRepo.getListResults()
////                        .subscribeOn(Schedulers.io())
////                        .subscribe({
////                            resultData.postValue(it)
////                            Log.v(TAG,it.toString())
////                        },{
////                            Log.v(TAG,it.toString())
////                        })
////
////        )
//
//    }
//
//    fun getAddress(){
//        compositeDisposable.add(
//                userRepo.getListAddress()
//                        .subscribeOn(Schedulers.io())
//                        .subscribe({
//                            addressData.postValue(it)
//                            Log.v(TAG,it.toString())
//                        },{
//                            Log.v(TAG,it.toString())
//                        })
//
//        )
//    }
//
//    fun deleteData(user: User) {
//        compositeDisposable.add(
//                userRepo.deleteUser(user)
//                        .flatMap {
//                            userRepo.getListUsers() }
//                        .subscribeOn(Schedulers.io())
//                        .subscribe({
//                           // list.remove(user)
//                            listData.postValue(it)
//
//                            Log.v(TAG,it.toString())
//
//                        },{
//                            Log.v(TAG,it.toString())
//                        })
//
//        )
//    }
//
//    fun deleteData(user: Results) {
//        compositeDisposable.add(
//                userRepo.deleteUser(user)
//                        .flatMap { userRepo.getListUsers() }
//                        .subscribeOn(Schedulers.io())
//                        .subscribe({
//                            // list.remove(user)
//                            listData.postValue(it)
//
//                            Log.v(TAG,it.toString())
//
//                        },{
//                            Log.v(TAG,it.toString())
//                        })
//
//        )
//    }

    fun updateResult(user: Results) {
        compositeDisposable.add(
                userRepo.updateResult(user)
                        .flatMap { userRepo.getListResults() }
                        .subscribeOn(Schedulers.io())
                        .subscribe({
                            // list.remove(user)
                            resultData.postValue(it)

                            Log.v(TAG, it.toString())

                        }, {
                            Log.v(TAG, it.toString())
                        })

        )
    }

//    fun updateUser(user: User) {
//        compositeDisposable.add(
//                userRepo.updateUser(user)
//                        .flatMap { userRepo.getListUsers() }
//                        .subscribeOn(Schedulers.io())
//                        .subscribe({
//                            // list.remove(user)
//                            listData.postValue(it)
//
//                            Log.v(TAG,it.toString())
//
//                        },{
//                            Log.v(TAG,it.toString())
//                        })
//
//        )
//    }

    override fun onCleared() {
        super.onCleared()
        compositeDisposable.dispose()
    }

}