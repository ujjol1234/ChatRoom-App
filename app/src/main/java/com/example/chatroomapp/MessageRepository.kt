package com.example.chatroomapp

import com.google.firebase.firestore.FirebaseFirestore
import kotlinx.coroutines.tasks.await
import com.example.chatroomapp.data.Result
import com.google.firebase.firestore.toObject
import kotlinx.coroutines.channels.awaitClose
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.callbackFlow

class MessageRepository(private val firestore:FirebaseFirestore) {

    suspend fun sendMessage(roomId:String,message:Message):Result<Unit> =
    try
    {
        firestore.collection("rooms").document(roomId).
                collection("messages").add(message).await()
        Result.Success(Unit)
    }
    catch (E:Exception){
        Result.Error(E)
    }
    suspend fun getChatMessages(roomId:String): Flow<List<Message>> =
        callbackFlow {
            val subscription=firestore.collection("rooms").
            document(roomId).collection("messages").
                    orderBy("timeStamp").
            addSnapshotListener {
                                                             querySnapShot, _ ->
                        querySnapShot?.let {
                            trySend(it.documents.map {
                                it.toObject(Message::class.java)!!.copy()
                            }).isSuccess
                        }
            }
            awaitClose{subscription.remove()}
            }

        }
//callbackFlow: Used to create a Flow that can emit values from callback-based APIs.(Firebase's API is also callback based)
//addSnapshotListener { querySnapshot, _ -> }: Sets up a listener that triggers whenever there are changes in the messages collection.
//querySnapshot?.let { ... }: If the querySnapshot is not null, it processes the snapshot.
//trySend(it.documents.map { doc -> doc.toObject(Message::class.java)!!.
// copy() }): Converts each document in the snapshot to a Message object,
// and sends the list of messages through the Flow using trySend.