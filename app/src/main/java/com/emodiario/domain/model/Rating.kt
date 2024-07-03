package com.emodiario.domain.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize
import java.util.Date

@Parcelize
data class Rating(
    val id: Int,
    val rating: CommuteRating,
    val description: String? = null,
    val date: Date
): Parcelable