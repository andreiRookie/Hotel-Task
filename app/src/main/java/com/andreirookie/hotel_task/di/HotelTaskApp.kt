package com.andreirookie.hotel_task.di

import android.app.Application
import android.content.Context

class HotelTaskApp : Application() {

    private var _applicationComponent: AppComponent? = null
    internal val applicationComponent: AppComponent
        get() = checkNotNull(_applicationComponent) { "ApplicationComponent is null" }

    override fun onCreate() {
        super.onCreate()
        _applicationComponent = DaggerAppComponent.factory().create(this)
    }

}

val Context.appComponent: AppComponent
    get() = when (this) {
            is HotelTaskApp -> applicationComponent
            else -> this.applicationContext.appComponent
        }
