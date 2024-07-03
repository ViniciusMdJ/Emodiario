package com.emodiario.domain.mapper

import com.emodiario.data.remote.dto.UserRequest
import com.emodiario.data.remote.dto.UserResponse
import com.emodiario.domain.model.User

fun UserResponse.toUser() = User(
    id = id ?: 0,
    name = name.orEmpty(),
    email = email.orEmpty(),
    phoneNumber = phoneNumber.orEmpty(),
    photoUrl = phoneNumber
)

fun User.toUserRequest(password: String) = UserRequest(
    id = id,
    name = name,
    email = email,
    phoneNumber = phoneNumber,
    password = password
)