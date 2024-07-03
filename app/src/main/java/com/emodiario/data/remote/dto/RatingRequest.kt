package com.emodiario.data.remote.dto

import com.google.gson.annotations.SerializedName

data class RatingRequest(
    @SerializedName("dataAtualizacao") val date: String? = null,
    @SerializedName("idCategoria") val activityId: Int? = null,
    @SerializedName("valor") val rating: Int? = null,
    @SerializedName("descricao") val description: String? = null,
)