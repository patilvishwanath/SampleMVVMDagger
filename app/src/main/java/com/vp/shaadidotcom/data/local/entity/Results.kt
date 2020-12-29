package com.vp.shaadidotcom.data.local.entity

import androidx.room.*


@Entity(tableName = "users")
data class Results(

        @PrimaryKey(autoGenerate = true)
        @ColumnInfo(name = "srId")
        var srId: Int = 0,

        @ColumnInfo(name = "gender")
        var gender: String,

        @ColumnInfo(name = "status")
        var status: String = "0",

        @Embedded
        var name: Name,

        @Ignore
        var location: Location = Location(Street(0,""),"","","","", Coordinates(0.0,0.0),Timezone("","")),

        @ColumnInfo(name = "email")
        var email: String,

        @Ignore
        var login: Login = Login("","","","","","",""),

        @Ignore
        var dob: Dob= Dob("",0),

        @Ignore
        var registered: Registered= Registered("",0),

        @Ignore
        var phone: String = "",

        @ColumnInfo(name = "cell")
        var cell: String,

        @Ignore
        var id: Id = Id("",""),

        @Ignore
        var picture: Picture = Picture("","",""),

        @Ignore
        var nat: String = ""
) {
      constructor() : this(gender = "",status = "0",name = Name("","",""),location = Location(Street(0,""),"","","","", Coordinates(0.0,0.0),Timezone("","")),
      email = "",
      login = Login("","","","","","",""),
      dob = Dob("",0),
      registered = Registered("",0),
      phone = "",
      cell = "",
      id= Id("",""),
      picture = Picture("","",""),
      nat = "")

}