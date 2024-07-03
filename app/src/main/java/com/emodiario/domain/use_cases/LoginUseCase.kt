package com.emodiario.domain.use_cases

import com.emodiario.data.repository.EmodiarioRepository
import com.emodiario.domain.mapper.toUser
import javax.inject.Inject

class LoginUseCase @Inject constructor(
    private val emodiarioRepository: EmodiarioRepository

) {
    suspend operator fun invoke(email: String, password: String) =
        emodiarioRepository.login(email, password).getOrThrow().toUser()
}