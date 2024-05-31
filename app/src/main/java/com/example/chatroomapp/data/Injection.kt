package com.example.chatroomapp.data

import com.google.firebase.firestore.FirebaseFirestore

//We will call this class injection just because we are trying to create a
// single instance of Firestore which is part of what Dependency Injection
// does

object Injection {
    private val instance: FirebaseFirestore by lazy{
        FirebaseFirestore.getInstance()
    }
    fun instance(): FirebaseFirestore{
        return instance
    }
}