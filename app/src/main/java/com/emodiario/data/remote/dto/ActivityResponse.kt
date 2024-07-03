package com.emodiario.data.remote.dto

import com.google.gson.annotations.SerializedName

data class ActivityResponse(
    @SerializedName("id") val id: Int? = null,
    @SerializedName("nome") val name: String? = null,
    @SerializedName("descricao") val description: String? = null
)
