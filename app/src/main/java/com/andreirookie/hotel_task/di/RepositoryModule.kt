package com.andreirookie.hotel_task.di

import com.andreirookie.hotel_task.dto.Hotel
import com.andreirookie.hotel_task.net.HotelNetModel
import com.andreirookie.hotel_task.net.NetRepository
import com.andreirookie.hotel_task.net.NetRepositoryImpl
import com.andreirookie.hotel_task.util.HotelMapper
import com.andreirookie.hotel_task.util.Mapper
import dagger.Binds
import dagger.Module

@Module
interface RepositoryModule {

    @FragmentScope
    @Binds
    fun bindNetRepository(impl: NetRepositoryImpl): NetRepository

    @FragmentScope
    @Binds
    fun bindHotelMapper(impl: HotelMapper): Mapper<HotelNetModel, Hotel>
}