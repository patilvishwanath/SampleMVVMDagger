package com.vp.shaadidotcom

import android.app.Application
import android.util.Log
import android.widget.Toast
import com.vp.shaadidotcom.di.component.ApplicationComponent
import com.vp.shaadidotcom.di.component.DaggerApplicationComponent
import com.vp.shaadidotcom.di.module.ApplicationModule
import java.lang.reflect.Method


/**
 * Created by Vishwanath Patil on 08/10/20.
 */
class UserApp : Application() {

    lateinit var applicationComponent: ApplicationComponent

    override fun onCreate() {
        super.onCreate()
        setUpDependency()
        showDebugDBAddressLogToast()
    }

    private fun setUpDependency() {
        applicationComponent =  DaggerApplicationComponent
                .builder()
                .applicationModule(ApplicationModule(this))
                .build()

        applicationComponent.inject(this)


    }


    fun showDebugDBAddressLogToast() {
        if (BuildConfig.DEBUG) {
            try {
                val debugDB = Class.forName("com.amitshekhar.DebugDB")
                val getAddressLog: Method = debugDB.getMethod("getAddressLog")
                val value: Any = getAddressLog.invoke(null)
                Toast.makeText(applicationContext, value as String, Toast.LENGTH_LONG).show()
                Log.v("Application",value)
            } catch (ignore: Exception) {
            }
        }
    }

}