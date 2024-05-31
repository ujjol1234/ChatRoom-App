package com.example.chatroomapp

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.chatroomapp.data.Injection
import com.google.firebase.auth.FirebaseAuth

@Composable
fun chatroomlist(roomViewModel: RoomViewModel= viewModel(),
                 onJoinClicked:(Room)->Unit){
    var showDialog by remember { mutableStateOf(false) }
    var RoomName by remember { mutableStateOf("") }
    val rooms by roomViewModel.rooms.observeAsState(emptyList())
    Column (Modifier.fillMaxSize()){
        Text("Chat Rooms", fontSize = 20.sp, fontWeight = FontWeight.Bold)
        LazyColumn(){
            items(rooms){
                RoomItem(room = it,onJoinClicked =
                {onJoinClicked(it)})
            }
        }

        Spacer(modifier = Modifier.height(16.dp))
        
        Button(onClick = { showDialog=true },
            Modifier
                .fillMaxWidth()
                .padding(8.dp)) {
            Text(text = "Add Room")
        }
        if(showDialog){
            AlertDialog(
                onDismissRequest = { showDialog = false },
                title = { Text(text = "Create New Room") },
                text = {
                    OutlinedTextField(
                        value = RoomName,
                        onValueChange = { RoomName = it },
                        singleLine = true,
                        modifier = Modifier
                            .padding(8.dp)
                            .fillMaxWidth()
                    )
                },
                confirmButton = {
                    Row (Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.SpaceBetween){
                        
                    Button(onClick = {
                        if(RoomName.isNotBlank()){
                            roomViewModel.createRoom(RoomName)
                            RoomName=""
                            showDialog=false

                        }                    },
                        Modifier.padding(8.dp)) {
                        Text(text = "Create Room")
                    }
                        Button(onClick = { showDialog=false },
                            Modifier.padding(8.dp)) {
                            Text(text = "Cancel")
                        }
                }}
            )
        }
        }
}