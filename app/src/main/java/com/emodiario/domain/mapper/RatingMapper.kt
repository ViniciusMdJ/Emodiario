package com.emodiario.domain.mapper

import com.emodiario.data.remote.dto.RatingRequest
import com.emodiario.data.remote.dto.RatingResponse
import com.emodiario.domain.model.CommuteRating
import com.emodiario.domain.model.Rating
import com.emodiario.presentation.common.toDate

fun RatingResponse.toRating() = Rating(
    id = id ?: 0,
    rating = rating!!.toCommuteRating(),
    description = description,
    date = date!!.toDate()
)

fun Rating.toRatingRequest(activityId: Int) = RatingRequest(
    activityId = activityId,
    date = date.toStringFormat("yyyy-MM-dd'T'00:00:00.000'Z'"),
    rating = rating.value,
    description = description
)