package com.emodiario.domain.use_cases

import com.emodiario.data.remote.dto.RatingRequest
import com.emodiario.data.repository.EmodiarioRepository
import javax.inject.Inject

class RegisterRatingUseCase @Inject constructor(
    private val emodiarioRepository: EmodiarioRepository

) {
    suspend operator fun invoke(activityId: Int, rating: RatingRequest) =
        emodiarioRepository.newActivityRating(activityId, rating).getOrThrow()
}