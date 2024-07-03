package com.emodiario.data.remote.dto

import com.google.gson.annotations.SerializedName

data class UserResponse(
    @SerializedName("id") val id: Int? = null,
    @SerializedName("nome") val name: String? = null,
    @SerializedName("email") val email: String? = null,
    @SerializedName("telefone") val phoneNumber: String? = null,
    @SerializedName("profile_picture") val profilePicture: String? = null,
    @SerializedName("categorias") val activities: List<ActivityResponse>? = null
)
