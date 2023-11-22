package com.andreirookie.hotel_task.di

import com.andreirookie.hotel_task.MainActivity
import dagger.Component
import javax.inject.Scope

@Scope
annotation class ActivityScope

@ActivityScope
@Component(
    modules = [ActivityModule::class],
    dependencies = [AppComponent::class])
interface ActivityComponent {

    fun inject(activity: MainActivity)

    @Component.Factory
    interface ActivityComponentFactory {
        fun create(appComponent: AppComponent): ActivityComponent
    }
}

object ActivityComponentHolder {
    private var activityComponent: ActivityComponent? = null

    fun getComponent(appComponent: AppComponent): ActivityComponent {
        return activityComponent ?: DaggerActivityComponent.factory().create(appComponent).also {
            activityComponent = it
        }
    }
}