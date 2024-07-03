package com.emodiario.domain.mapper

import com.emodiario.domain.model.CommuteRating
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale

fun Int?.toCommuteRating() = when {
    this!! <= 1 -> CommuteRating.TERRIBLE
    this == 2 -> CommuteRating.BAD
    this == 3 -> CommuteRating.AVERAGE
    this == 4 -> CommuteRating.GOOD
    else -> CommuteRating.EXCELLENT
}

fun Date.toStringFormat(format: String): String {
    val sdf = SimpleDateFormat(format, Locale.getDefault())
    return sdf.format(this)
}