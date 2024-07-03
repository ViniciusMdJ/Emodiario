package com.emodiario.data.remote.dto

import com.google.gson.annotations.SerializedName

data class ActivityRequest(
    @SerializedName("nome") val name: String? = null,
    @SerializedName("descricao") val description: String = ""
)

