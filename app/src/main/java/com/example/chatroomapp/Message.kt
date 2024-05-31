package com.example.chatroomapp

data class Message(
    val senderFirstName:String = "",
    val senderLastName:String="",
    val senderMail:String= "",
    val text:String = "",
    val sentByCurrentUser: Boolean = false,
    val timeStamp:Long =System.currentTimeMillis()
)