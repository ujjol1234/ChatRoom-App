package com.example.chatroomapp

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.chatroomapp.data.Injection
import com.example.chatroomapp.data.Result
import com.google.firebase.auth.FirebaseAuth
import kotlinx.coroutines.launch

class AuthViewModel : ViewModel() {
    private val userRepository: UserRepository
    init {
        userRepository= UserRepository(
            FirebaseAuth.getInstance(),
            Injection.instance()
        )
    }
    private val _authResult = MutableLiveData<Result<Boolean>>()
    val authResult: LiveData<Result<Boolean>> get() = _authResult

    fun signUp(email: String, password: String, firstName: String, lastName: String) {
        viewModelScope.launch {
            _authResult.value = userRepository.SignUp(firstName,lastName,password,email)
        }
    }
    fun LogIn(email: String,password:String){
        viewModelScope.launch {
            _authResult.value = userRepository.LogIn(email,password)
        }
    }
}