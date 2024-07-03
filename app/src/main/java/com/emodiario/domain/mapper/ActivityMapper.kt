package com.emodiario.domain.mapper

import com.emodiario.data.remote.dto.ActivityRequest
import com.emodiario.data.remote.dto.ActivityResponse
import com.emodiario.domain.model.Activity

fun ActivityResponse.toActivity() = Activity(
    id = id ?: 0,
    name = name.orEmpty(),
    description = description.orEmpty(),
)

fun Activity.toActivityRequest()  = ActivityRequest(
    name = name,
    description = description ?: "",
)