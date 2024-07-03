package com.emodiario.domain.use_cases

import com.emodiario.data.repository.EmodiarioRepository
import com.emodiario.domain.mapper.toRating
import javax.inject.Inject

class GetRatingHistoryUseCase @Inject constructor(
    private val emodiarioRepository: EmodiarioRepository
) {
    suspend operator fun invoke(activityId: Int) =
        emodiarioRepository.getActivityRatings(activityId).getOrThrow().map { it.toRating() }
}