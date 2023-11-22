package com.andreirookie.hotel_task.net

import com.andreirookie.hotel_task.dto.Hotel


interface NetRepository {
    suspend fun loadHotel(): Hotel
}