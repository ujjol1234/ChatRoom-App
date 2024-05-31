package com.example.chatroomapp



import android.os.Build
import android.view.RoundedCorner
import androidx.annotation.RequiresApi
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun MessageItem(message: Message) {
    Column (horizontalAlignment =  if (message.sentByCurrentUser)
        Alignment.End else Alignment.Start,
        modifier = Modifier.fillMaxWidth()){
        Box (modifier = Modifier.background(
            if(message.sentByCurrentUser) colorResource(id = R.color.purple_700)
            else Color.Gray,
            shape = RoundedCornerShape(8.dp)
        )){
            Text(text = message.text,
                color = Color.White,
                style = TextStyle(fontSize = 16.sp)
            )
            
        }
        Spacer(modifier = Modifier.height(4.dp))
        Text(text = message.senderFirstName,
            style = TextStyle(
                fontSize = 12.sp,
                color = Color.Gray))
        Text(text = formatTimestamp(message.timeStamp) )


    }
}