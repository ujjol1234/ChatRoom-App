package com.example.chatroomapp

import android.os.Build
import android.util.Log
import androidx.annotation.RequiresApi
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Send
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel

@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun ChatScreen(roomId: String,
               messageViewModel:
               MessageViewModel = viewModel()){

    val text = remember { mutableStateOf("") }
    val messages by messageViewModel.messages.observeAsState(emptyList())
    messageViewModel.setRoomId(roomId)
    Column (
        Modifier
            .fillMaxSize()
            .padding(18.dp)) {
        LazyColumn(modifier = Modifier.weight(1f)) {
            items(messages){
                MessageItem(message = it.copy(sentByCurrentUser =
                it.senderMail==messageViewModel.currentUser.value?.email))
            }
        }
        Row (verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier.fillMaxWidth()){
            BasicTextField(value = text.value,
                onValueChange = {text.value=it},
                textStyle = TextStyle.Default.copy(fontSize = 16.sp),
                modifier = Modifier
                    .weight(1f)
                    .padding(8.dp)
                )
            IconButton(onClick = {
                if(text.value.isNotEmpty()){
                    messageViewModel.sendmessage(text.value.trim(),
                        time = System.currentTimeMillis())
                    text.value=""
                }
                messageViewModel.loadMessages()
            }) {
                Icon(imageVector = Icons.Default.Send, contentDescription = "Send")
            }
        }
    }
}