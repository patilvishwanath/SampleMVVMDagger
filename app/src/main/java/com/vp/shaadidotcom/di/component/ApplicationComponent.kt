package com.vp.shaadidotcom.di.component

import com.vp.shaadidotcom.UserApp
import com.vp.shaadidotcom.data.local.dao.UserDao
import com.vp.shaadidotcom.data.local.db.DataBaseService
import com.vp.shaadidotcom.data.remote.NetworkService
import com.vp.shaadidotcom.data.repository.UserRepo
import com.vp.shaadidotcom.di.module.ActivityModule
import com.vp.shaadidotcom.di.module.ApplicationModule
import dagger.Component
import io.reactivex.disposables.CompositeDisposable
import javax.inject.Singleton

/**
 * Created by Vishwanath Patil on 08/10/20.
 */
@Singleton
@Component(modules = [ApplicationModule::class])
interface ApplicationComponent {

    fun inject(userApp: UserApp)

    fun getDataBaseService() : DataBaseService

    fun getUserRepo() : UserRepo

    fun getCompositeDisposable() : CompositeDisposable

    fun getNetworkService() : NetworkService

}