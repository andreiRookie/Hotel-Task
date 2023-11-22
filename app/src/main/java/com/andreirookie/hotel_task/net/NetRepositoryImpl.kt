package com.andreirookie.hotel_task.net

import com.andreirookie.hotel_task.dto.Hotel
import com.andreirookie.hotel_task.util.Mapper
import javax.inject.Inject

class NetRepositoryImpl @Inject constructor(
    private val service: ApiService,
    private val mapper: Mapper<HotelNetModel, Hotel>
) : NetRepository {

    override suspend fun loadHotel(): Hotel {
        return service.getHotel().let { netModel ->
            mapper.mapFromEntity(netModel)
        }
    }
}