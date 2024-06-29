package com.emodiario.domain.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class Activity(
    val id: String,
    val name: String,
    val description : String? = null
): Parcelable
