package com.emodiario.domain.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class Activity(
    val id: Int,
    val name: String,
    val description : String? = null
): Parcelable
