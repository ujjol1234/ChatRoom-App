package com.example.chatroomapp

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.runtime.Composable
import androidx.navigation.NavController
import androidx.navigation.NavHostController
import androidx.navigation.Navigation
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable

sealed class Screen(val title:String,val route:String){
    object SignInScreen:Screen("Sign In","SignInScreen")
    object LogInScreen:Screen("Log In","LogInScreen")
    object ChatRoomListScreen:Screen("Chat Room List","ChatRoomList")
    object ChatScreen:Screen("Chat Screen","ChatScreen")
}

@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun Navigation(navHostController: NavHostController,
               authViewModel: AuthViewModel){
    NavHost(navController = navHostController,
        startDestination = Screen.SignInScreen.route){
        composable(Screen.SignInScreen.route){
            SignUpScreen(authViewModel = authViewModel,
                OnNavigateToLogIn = {
                navHostController.navigate(Screen.LogInScreen.route)
            })
        }
        composable(Screen.LogInScreen.route){
            LogInScreen (authViewModel = authViewModel,
                onNavigateTosignin = {
                navHostController.navigate(Screen.SignInScreen.route)
            },
                onLogInSuccess = {navHostController.navigate(Screen.ChatRoomListScreen.route)})
        }
        composable(Screen.ChatRoomListScreen.route){
                chatroomlist( onJoinClicked = {
                        navHostController.navigate("${Screen.ChatScreen.route}/${it.roomId}")
                })
        }
        composable("${Screen.ChatScreen.route}/{roomId}"){
            val roomId:String = it.arguments?.getString("roomId") ?:""
            ChatScreen(roomId = roomId)
        }

    }
}