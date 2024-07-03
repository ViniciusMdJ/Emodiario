package com.emodiario.domain.use_cases

import com.emodiario.data.repository.EmodiarioRepository
import com.emodiario.domain.mapper.toUser
import javax.inject.Inject

class GetProfileUseCase @Inject constructor(
    private val emodiarioRepository: EmodiarioRepository
) {
    suspend operator fun invoke(userId: Int) =
        emodiarioRepository.getUser(userId).getOrThrow().toUser()
}