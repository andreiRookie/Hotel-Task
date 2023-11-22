package com.andreirookie.hotel_task.di

import com.andreirookie.hotel_task.ui.hotel.HotelScreenFragment
import dagger.Component

@FragmentScope
@Component(
    modules = [NetworkModule::class, RepositoryModule::class],
    dependencies = [ActivityComponent::class]
)
interface HotelScreenComponent {

    fun inject(fragment: HotelScreenFragment)

    @Component.Factory
    interface HotelScreenComponentFactory {
        fun create(activityComponent: ActivityComponent): HotelScreenComponent
    }

    companion object {
        fun getComponent(activityComponent: ActivityComponent): HotelScreenComponent {
           return DaggerHotelScreenComponent.factory().create(activityComponent)
        }
    }
}