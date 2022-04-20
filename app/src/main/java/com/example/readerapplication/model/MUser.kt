package com.example.readerapplication.model

data class MUser(
    val userId: String,
    val displayName: String,
    val profession: String?,
    val quotes: String?,
    val id: String,
    val avatarUrl: String,
) {

    fun toMap(): Map<String, Any?> =
        mutableMapOf(
            "user_id" to userId,
            "display_name" to displayName,
            "profession" to profession,
            "quotes" to quotes,
            "id" to id,
            "avatar_url" to avatarUrl
        )
}