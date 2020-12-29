package com.vp.shaadidotcom.di.component

import androidx.appcompat.app.AppCompatActivity
import com.vp.shaadidotcom.di.ActivityScope
import com.vp.shaadidotcom.di.module.ActivityModule
import com.vp.shaadidotcom.ui.DashboardActivity
import dagger.Component

/**
 * Created by Vishwanath Patil on 08/10/20.
 */
@ActivityScope
@Component(dependencies = [ApplicationComponent::class],modules = [ActivityModule::class])
interface ActivityComponent  {

    fun inject(activity: DashboardActivity)

}