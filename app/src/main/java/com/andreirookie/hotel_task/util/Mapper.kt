package com.andreirookie.hotel_task.util

interface Mapper<E, M> {
    fun mapFromEntity(e: E): M
}