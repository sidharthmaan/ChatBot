package com.example.chatbot

import android.util.Log
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.core.app.NotificationCompat.MessagingStyle.Message
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.google.ai.client.generativeai.Chat
import com.google.ai.client.generativeai.GenerativeModel
import com.google.ai.client.generativeai.java.GenerativeModelFutures
import com.google.ai.client.generativeai.type.content
import kotlinx.coroutines.launch

class ChatBotVM : ViewModel() {
    val list by lazy {
        mutableStateListOf<ChatData>()
    }
    private val genAi by lazy {
        GenerativeModel(
            modelName = "gemini-pro",
            apiKey = ApiKey
        )
    }

    fun sendMessage(message: String)= viewModelScope.launch {
//        var responce = genAi.startChat().sendMessage(prompt = message).text

        val chat: Chat = genAi.startChat()
        list.add(ChatData(message, ChatRoleEnum.User.role))

        chat.sendMessage(
            content(ChatRoleEnum.User.role) { text(message) }
        ).text?.let {
            list.add(ChatData(it, ChatRoleEnum.Model.role))
        }

//        Log.d("AI_ANS", responce.toString())
    }
}