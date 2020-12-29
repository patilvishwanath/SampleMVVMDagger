package com.vp.shaadidotcom.di.module

import android.content.Context
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.vp.shaadidotcom.data.repository.UserRepo
import com.vp.shaadidotcom.ui.DashboardViewModel
import com.vp.shaadidotcom.utils.ViewModelProviderFactory
import dagger.Module
import dagger.Provides
import io.reactivex.disposables.CompositeDisposable

/**
 * Created by Vishwanath Patil on 08/10/20.
 */
@Module
class ActivityModule(private val activity: AppCompatActivity) {

    @Provides
    fun provideContext() : Context {
        return activity
    }

    @Provides
    fun provideDashboardViewModel(userRepo: UserRepo,compositeDisposable: CompositeDisposable) : DashboardViewModel {
        return ViewModelProvider(activity,ViewModelProviderFactory(DashboardViewModel::class){
            DashboardViewModel(userRepo,compositeDisposable)
        }).get(DashboardViewModel::class.java)
    }

}