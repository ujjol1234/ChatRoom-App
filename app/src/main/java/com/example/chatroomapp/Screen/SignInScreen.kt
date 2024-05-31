package com.example.chatroomapp

import android.util.Log
import android.widget.Toast
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.chatroomapp.data.Result

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SignUpScreen(OnNavigateToLogIn:() -> Unit,
                 authViewModel: AuthViewModel){
    var email by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }
    var firstName by remember { mutableStateOf("") }
    var lastName by remember { mutableStateOf("") }

    Column (
        Modifier
            .fillMaxSize()
            .padding(16.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally){
        OutlinedTextField(value = email,
            onValueChange = {email=it},
            label = { Text("Email") },
            modifier = Modifier.padding(8.dp))
        OutlinedTextField(
            value = password,
            onValueChange = {password = it},
            label = { Text("Password") },
            modifier = Modifier.padding(8.dp),
            visualTransformation = PasswordVisualTransformation())
        OutlinedTextField(value = firstName,
            onValueChange = {firstName=it},
            label = { Text("First Name") },
            modifier = Modifier.padding(8.dp))
        OutlinedTextField(value = lastName,
            onValueChange = {lastName=it},
            label = { Text("Last Name") },
            modifier = Modifier.padding(8.dp))
        Button(onClick = {
            authViewModel.signUp(email,password,firstName,lastName)
            email=""
            password=""
            firstName=""
            lastName=""
        }) {
            Text(text = "Sign In")
        }
        Spacer(modifier = Modifier.height(16.dp))
        Text("Already have an account? Sign in.",
            modifier = Modifier.clickable { OnNavigateToLogIn() }
        )
    }
}

//@Preview
//@Composable
//fun SignUpScreenPreview() {
//    SignUpScreen()
//}