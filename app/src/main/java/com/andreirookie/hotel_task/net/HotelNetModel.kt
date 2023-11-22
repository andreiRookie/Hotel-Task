package com.andreirookie.hotel_task.net

import com.squareup.moshi.Json

data class HotelNetModel(
    @field:Json(name = "id")
    val id: Int,
    @field:Json(name = "name")
    val name: String,
    @field:Json(name = "adress")
    val address: String,
    @field:Json(name = "minimal_price")
    val minimalPrice:Int,
    @field:Json(name = "price_for_it")
    val priceForIt: String,
    @field:Json(name = "rating")
    val rating: Int,
    @field:Json(name = "rating_name")
    val ratingName: String,
    @field:Json(name = "image_urls")
    val imageUrls: List<String>,
    @field:Json(name = "about_the_hotel")
    val aboutTheHotel: AboutTheHotelNetModel
)

data class AboutTheHotelNetModel(
    @field:Json(name = "description")
    val description: String,
    @field:Json(name = "peculiarities")
    val peculiarities: List<String>
)
