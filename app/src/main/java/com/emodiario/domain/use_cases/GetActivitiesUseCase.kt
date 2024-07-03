package com.emodiario.domain.use_cases

import com.emodiario.data.repository.EmodiarioRepository
import com.emodiario.domain.mapper.toActivity
import javax.inject.Inject

class GetActivitiesUseCase @Inject constructor(
    private val emodiarioRepository: EmodiarioRepository
) {
    suspend operator fun invoke(userId: Int) =
        emodiarioRepository.getActivities(userId).getOrThrow().map { it.toActivity() }
}