package com.bangkit.faniabdullah_jetpack

import android.app.Application
import com.bangkit.faniabdullah_jetpack.di.ApplicationComponent
import com.bangkit.faniabdullah_jetpack.di.DaggerApplicationComponent

open class MyApplication : Application() {

    val appComponent: ApplicationComponent by lazy {
        DaggerApplicationComponent.factory().create(applicationContext)
    }
}