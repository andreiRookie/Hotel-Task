package com.andreirookie.hotel_task.util

import com.andreirookie.hotel_task.dto.AboutTheHotel
import com.andreirookie.hotel_task.dto.Hotel
import com.andreirookie.hotel_task.net.HotelNetModel
import javax.inject.Inject

class HotelMapper @Inject constructor() : Mapper<HotelNetModel, Hotel> {
    override fun mapFromEntity(e: HotelNetModel): Hotel {
        return Hotel(
            id = e.id,
            name = e.name,
            address = e.address,
            minimalPrice = e.minimalPrice.toString(),
            priceForIt = e.priceForIt.lowercase(),
            rating = e.rating.toString(),
            ratingName = e.ratingName,
            imageUrls = e.imageUrls,
            aboutTheHotel = AboutTheHotel(
                e.aboutTheHotel.description,
                e.aboutTheHotel.peculiarities
            )
        )
    }
}