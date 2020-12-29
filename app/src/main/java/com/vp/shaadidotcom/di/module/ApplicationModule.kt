package com.vp.shaadidotcom.di.module

import android.content.Context
import androidx.room.Room
import com.vp.shaadidotcom.UserApp
import com.vp.shaadidotcom.data.local.db.DataBaseService
import com.vp.shaadidotcom.data.remote.NetworkService
import com.vp.shaadidotcom.data.remote.RetrofitObject
import dagger.Module
import dagger.Provides
import io.reactivex.disposables.CompositeDisposable
import javax.inject.Singleton

/**
 * Created by Vishwanath Patil on 08/10/20.
 */
@Module
class ApplicationModule(private val userApp: UserApp) {

    @Provides
    fun provideContext() : Context{
        return userApp
    }

    @Singleton
    @Provides
    fun provideDb(): DataBaseService {
        return Room
                .databaseBuilder(userApp,
                        DataBaseService::class.java,
                        "user_db")
                .build()
    }

    @Provides
    fun provideCompositeDisposable() : CompositeDisposable{
        return CompositeDisposable()
    }

    @Provides
    @Singleton
    fun providesNetworkService() : NetworkService  {
        return RetrofitObject.createRetroFit(
                "https://randomuser.me/"
        )
    }





}