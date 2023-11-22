package com.andreirookie.hotel_task.di


import com.andreirookie.hotel_task.net.NetRepository
import com.andreirookie.hotel_task.ui.hotel.HotelScreenViewModel
import dagger.Module
import dagger.Provides

@Module
object ActivityModule {

    @ActivityScope
    @Provides
    fun provideHotelScreenViewModel(
        netRepository: NetRepository
    ): HotelScreenViewModel {
        return HotelScreenViewModel.Factory(netRepository)
            .create(HotelScreenViewModel::class.java)
    }
}