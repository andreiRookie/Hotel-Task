package com.andreirookie.hotel_task.dto

data class Hotel(
    val id: Int,
    val name: String,
    val address: String,
    val minimalPrice: String,
    val priceForIt: String,
    val rating: String,
    val ratingName: String,
    val imageUrls: List<String>,
    val aboutTheHotel: AboutTheHotel
)

data class AboutTheHotel(
    val description: String,
    val peculiarities: List<String>
)
