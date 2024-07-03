package com.emodiario.data.remote.dto

import com.google.gson.annotations.SerializedName

data class RatingResponse(
    @SerializedName("id") val id: Int? = null,
    @SerializedName("valor") val rating: Int? = null,
    @SerializedName("descricao") val description: String? = null,
    @SerializedName("dataAtualizacao") val date: String? = null
)