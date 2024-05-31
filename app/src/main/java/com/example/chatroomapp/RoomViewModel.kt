package com.example.chatroomapp

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.chatroomapp.data.Injection
import com.example.chatroomapp.data.Result
import kotlinx.coroutines.launch

class RoomViewModel:ViewModel() {
    private val _rooms = MutableLiveData<List<Room>>()
    val rooms:LiveData<List<Room>> get() = _rooms
    private val roomRepository: RoomRepository
    init {
        roomRepository = RoomRepository(Injection.instance())
        loadRooms()
    }
    fun createRoom(RoomName:String){
        viewModelScope.launch {
            roomRepository.createRoom(RoomName)
        }
        loadRooms()
    }
    fun loadRooms() {
        viewModelScope.launch {
            when (val result = roomRepository.getRooms()) {
                is Result.Success -> _rooms.value = result.data
                is Result.Error -> {

                }
            }
        }
    }



}