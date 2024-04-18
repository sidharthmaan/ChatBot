package com.example.chatbot

import java.lang.Error

sealed interface ChatBotUiState{
    data object Ideal : ChatBotUiState

    data object Loading : ChatBotUiState

    data class Success(val ChatData: String) : ChatBotUiState

    data class Error (val chatError: String):ChatBotUiState
}