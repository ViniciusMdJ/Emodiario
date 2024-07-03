package com.emodiario.data.remote.dto

import com.google.gson.annotations.SerializedName

data class UserRequest(
    @SerializedName("id") val id: Int? = null,
    @SerializedName("nome") val name: String? = null,
    @SerializedName("email") val email: String? = null,
    @SerializedName("senha") val password: String? = null,
    @SerializedName("telefone") val phoneNumber: String? = null
)
