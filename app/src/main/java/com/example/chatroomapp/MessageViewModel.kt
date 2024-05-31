package com.example.chatroomapp

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.chatroomapp.data.Injection
import com.example.chatroomapp.data.Result
import com.example.chatroomapp.data.User
import com.google.firebase.auth.FirebaseAuth
import kotlinx.coroutines.launch
import kotlin.math.log

class MessageViewModel:ViewModel() {
    private val userRepository: UserRepository
    private val messageRepository: MessageRepository

    init {
        userRepository = UserRepository(
            auth = FirebaseAuth.getInstance(),
            Injection.instance()
        )
        messageRepository = MessageRepository(Injection.instance())
        viewModelScope.launch {
            loadcurrentuser()
        }
    }

    private val _messages = MutableLiveData<List<Message>>()
    val messages: LiveData<List<Message>> get() = _messages
    private val roomId = MutableLiveData<String>()
    private val _currentUser = MutableLiveData<User>()
    val currentUser: LiveData<User> get() = _currentUser

    suspend fun loadcurrentuser() {
        viewModelScope.launch {
            when (val result = userRepository.loadcurrentuser()) {
                is Result.Success -> {
                    _currentUser.value = result.data
                }
                is Result.Error -> {

                }

            }
        }
    }
    fun loadMessages() {
        viewModelScope.launch {
            if (roomId != null) {
                messageRepository.getChatMessages(roomId.value.toString())
                    .collect { _messages.value = it }
            }
        }
    }
    fun sendmessage(text:String,
                    time:Long){
        if(_currentUser.value!=null){
            val message = Message(
                senderFirstName = currentUser.value!!.firstName, //!! = non-null value assertion
                senderLastName = currentUser.value!!.lastName,
                senderMail = currentUser.value!!.email,
                text = text,
                timeStamp =time
            )
            viewModelScope.launch {
                when(messageRepository.sendMessage(roomId.value.toString(),message)){
                    is Result.Success -> Unit
                    is Result.Error->{}
                }
            }

        }

    }
    fun setRoomId(roomid: String) {
        roomId.value = roomid
        loadMessages()
    }
}