package com.vp.shaadidotcom.data.remote

import com.jakewharton.retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import com.vp.shaadidotcom.BuildConfig
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

/**
 * Created by Vishwanath Patil on 23/11/20.
 */
object RetrofitObject {

    private const val TIMEOUT = 60

    fun createRetroFit(baseUrl : String) : NetworkService {
        return Retrofit.Builder()
                .baseUrl(baseUrl)
                .client(
                        OkHttpClient.Builder()

                                .addInterceptor(
                                        HttpLoggingInterceptor().apply {
                                            level = if (BuildConfig.DEBUG) HttpLoggingInterceptor.Level.BODY
                                            else HttpLoggingInterceptor.Level.NONE
                                        })
                                .connectTimeout(TIMEOUT.toLong(),TimeUnit.SECONDS)
                                .readTimeout(TIMEOUT.toLong(), TimeUnit.SECONDS)
                                .build()
                )
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .build()
                .create(NetworkService::class.java)

    }


}