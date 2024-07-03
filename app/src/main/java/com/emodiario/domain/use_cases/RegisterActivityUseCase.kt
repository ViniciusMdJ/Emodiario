package com.emodiario.domain.use_cases

import com.emodiario.data.remote.dto.ActivityRequest
import com.emodiario.data.repository.EmodiarioRepository
import com.emodiario.domain.mapper.toActivity
import javax.inject.Inject

class RegisterActivityUseCase @Inject constructor(
    private val emodiarioRepository: EmodiarioRepository

) {
    suspend operator fun invoke(userId: Int, activity: ActivityRequest) =
        emodiarioRepository.createActivity(userId, activity).getOrThrow().toActivity()
}