package com.vp.shaadidotcom.data.local.entity

import androidx.room.ColumnInfo
import androidx.room.Ignore

data class Namee(

        @Ignore
        var title: String = "",
        @ColumnInfo(name = "first")
        var first: String,
        @ColumnInfo(name = "last")
        var last: String

) {
    constructor() : this(title = "",first = "",last = "")
}
