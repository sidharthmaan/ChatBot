package com.example.chatbot

data class ChatData (
    val message : String,
    val role : String
)

enum class ChatRoleEnum(val role : String){
    User("user"),
    Model("model")
}