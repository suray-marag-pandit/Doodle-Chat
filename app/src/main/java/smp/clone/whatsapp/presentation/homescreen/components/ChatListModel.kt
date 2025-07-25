package smp.clone.whatsapp.presentation.homescreen.components

data class ChatListModel(
    val id: String,
    val text: String,
    val senderId: String,
    val time: String,
    val isSentByMe: Boolean,
    val senderImageResId: Int? = null
)