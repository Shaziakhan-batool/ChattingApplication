package com.example.chattingapplication.chatapp

import com.google.android.play.core.integrity.t

class Message {
    var message:String?=null
    var senderId: String?=null

    constructor(){}

    constructor(message: String?,senderId: String?){
        this.message=message
        this.senderId=senderId

    }
}