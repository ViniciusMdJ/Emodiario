package com.emodiario.domain.model

data class User(
    val id: Int,
    val name: String,
    val email: String,
    val phoneNumber: String,
    val photoUrl: String?
)