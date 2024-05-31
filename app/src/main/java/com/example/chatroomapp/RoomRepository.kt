package com.example.chatroomapp

import com.google.firebase.firestore.FirebaseFirestore
import kotlinx.coroutines.tasks.await
import com.example.chatroomapp.data.Result


class RoomRepository(private val firestore: FirebaseFirestore) {

    suspend fun createRoom(name: String): Result<Unit> = try {
        val room = Room(roomName = name)
        firestore.collection("rooms").add(room).await()
        Result.Success(Unit)
    } catch (e: Exception) {
        Result.Error(e)
    }
    suspend fun getRooms():Result<List<Room>> = try {
        val query = firestore.collection("rooms").get().await()
        val rooms = query.documents.map {
            it.toObject(Room::class.java)!!.copy(
                roomId = it.id)
        }
        Result.Success(rooms)
    }
    catch (e: Exception) {
        Result.Error(e)
    }
}
//The createRoom Function will get the name of the room and save it to
// its own collection called room. The getRooms function will return a
// list of Room by querying the rooms collection then map the documents
// into a list including the id for each object This id will enable us to
// identify each rooms during chats.