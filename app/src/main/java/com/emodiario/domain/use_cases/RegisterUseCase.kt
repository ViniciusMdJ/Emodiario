package com.emodiario.domain.use_cases

import com.emodiario.data.remote.dto.UserRequest
import com.emodiario.data.repository.EmodiarioRepository
import com.emodiario.domain.mapper.toUser
import javax.inject.Inject

class RegisterUseCase @Inject constructor(
    private val emodiarioRepository: EmodiarioRepository

) {
    suspend operator fun invoke(newUser: UserRequest) =
        emodiarioRepository.register(newUser).getOrThrow().toUser()
}