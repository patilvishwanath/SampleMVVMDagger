package com.vp.shaadidotcom.data.local.dao


import androidx.room.*
import com.vp.shaadidotcom.data.local.entity.Addresss
import com.vp.shaadidotcom.data.local.entity.Results
import io.reactivex.Single

@Dao
interface AddressDao {

    @Insert
     fun insertAllAddress(list : List<Addresss>) : Single<List<Long>>

    @Delete
    fun deleteAddress(addresss: Addresss) : Single<Int>

    @Query("SELECT * FROM address")
    fun getAllAddress(): Single<List<Addresss>>

    @Update
    fun updateAddress(addresss: Addresss): Single<Int>

    @Query("SELECT COUNT(*) FROM address")
    fun getCountAddress() : Single<Int>



}
