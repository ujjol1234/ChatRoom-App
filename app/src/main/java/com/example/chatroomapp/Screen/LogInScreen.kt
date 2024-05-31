package com.example.chatroomapp

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.chatroomapp.data.Result

@Composable
fun LogInScreen(onNavigateTosignin:()->Unit,
                authViewModel: AuthViewModel,
                onLogInSuccess:() -> Unit){
    var email by remember { mutableStateOf("") }
    var password by remember {
        mutableStateOf("")
    }
    val result by authViewModel.authResult.observeAsState()
    Column (verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier= Modifier
            .fillMaxSize()
            .padding(8.dp)){
        OutlinedTextField(value = email,
            onValueChange = {email=it},
            label = { Text("Email") },
            modifier = Modifier.padding(8.dp))
        OutlinedTextField(value = password,
            onValueChange = {password=it},
            label = { Text("Password") },
            modifier = Modifier.padding(8.dp))
        Button(onClick = {
            authViewModel.LogIn(email,password)
            when(result){
              is Result.Success->{
                    onLogInSuccess()
              }
                is Result.Error ->{

                }
                else ->{}
            }
        }) {
            Text(text = "Log In")
        }
        Spacer(modifier = Modifier.height(16.dp))
        Text("Don't have an account? Sign up.",
            modifier = Modifier.clickable { onNavigateTosignin() }
        )
    }
}