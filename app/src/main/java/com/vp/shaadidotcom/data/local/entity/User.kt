package com.vp.shaadidotcom.data.local.entity


import androidx.room.*

/**
 * Created by Vishwanath Patil on 08/10/20.
 */
@Entity(tableName = "users_db")
data class User(
        @PrimaryKey(autoGenerate = true)
        @ColumnInfo(name = "srId")
        var srId: Long = 0,

        @Embedded
        var name: Namee,

        @ColumnInfo(name = "status")
        var status: Int = 0,

        @Ignore
        @ColumnInfo(name = "selected")
        var selected: Boolean = false







) {
        constructor() : this(name = Namee(title = "",first = "",last = ""),status = 0,selected = false)
}