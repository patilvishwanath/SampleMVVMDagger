package com.vp.shaadidotcom.data.local.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.PrimaryKey
import java.util.*

@Entity(tableName = "address",
        foreignKeys = [ForeignKey(entity = User::class, parentColumns = ["srId"], childColumns = ["userId"], onDelete = ForeignKey.CASCADE)])
data class Addresss(

        @PrimaryKey(autoGenerate = true)
        var id: Long = 0,

        @ColumnInfo(name = "city")
        var city: String,

        @ColumnInfo(name = "pincode")
        var pincode: String,

        @ColumnInfo(name = "userId")
        var userId: String

//        @ColumnInfo
//        var date: Long


) {

    constructor() : this(city = "", pincode = "", userId = "")//,date = 0)


}
