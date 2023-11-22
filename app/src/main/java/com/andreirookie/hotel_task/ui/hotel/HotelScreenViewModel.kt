package com.andreirookie.hotel_task.ui.hotel

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import com.andreirookie.hotel_task.dto.Hotel
import com.andreirookie.hotel_task.net.NetRepository
import kotlinx.coroutines.CancellationException
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject
import kotlin.Exception

class HotelScreenViewModel @Inject constructor(
    private val repository: NetRepository
) : ViewModel() {

    private val _state = MutableStateFlow<HotelScreenState>(HotelScreenState.Init)
    val state: StateFlow<HotelScreenState> get() = _state

    init {
        loadHotel()
    }

    fun loadHotel() {
        _state.value = HotelScreenState.Loading
        viewModelScope.launch {
            try {
                val hotel = repository.loadHotel()
                Log.e("AAA", hotel.toString())
                _state.value = HotelScreenState.Data(hotel)
            } catch (e: CancellationException) {
                throw e
            } catch (e: Exception) {
                Log.e("AAA", e.message.toString())
                _state.value = HotelScreenState.Error(e)
            }
        }
    }

    companion object {
        fun Factory(netRepository: NetRepository) = object : ViewModelProvider.Factory {
            override fun <T : ViewModel> create(modelClass: Class<T>): T {
                return when (modelClass) {
                    HotelScreenViewModel::class.java -> {
                        HotelScreenViewModel(netRepository) as T
                    }
                    else -> {
                        error("Unknown $modelClass")
                    }
                }
            }
        }


    }
}

sealed interface HotelScreenState {
    data class Data(val hotel: Hotel) : HotelScreenState
    data object Loading : HotelScreenState
    data object Init : HotelScreenState
    data class Error(val ex: Exception) : HotelScreenState
}