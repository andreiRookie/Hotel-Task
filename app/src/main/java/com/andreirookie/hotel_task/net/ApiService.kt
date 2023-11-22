package com.andreirookie.hotel_task.net

import retrofit2.http.GET

interface ApiService {

    @GET(value = "/v3/d144777c-a67f-4e35-867a-cacc3b827473")
    suspend fun getHotel(): HotelNetModel
}