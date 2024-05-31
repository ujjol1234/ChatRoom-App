package com.example.chatroomapp

data class Room(val roomName:String="",
                val roomId:String="")

//The name in firebase should be the same name as in the class or else the data will not be mapped correctly