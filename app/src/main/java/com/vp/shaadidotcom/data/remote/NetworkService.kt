package com.vp.shaadidotcom.data.remote

import com.vp.shaadidotcom.data.local.entity.Base
import io.reactivex.Observable
import io.reactivex.Single
import retrofit2.http.GET
import retrofit2.http.Query

/**
 * Created by Vishwanath Patil on 11/10/20.
 */

interface NetworkService {

    @GET("api/")
    fun getResults(
            @Query("results") size: String
    ): Single<Base>


}