package com.emodiario.domain.model

enum class CommuteRating(val nameValue: String, val value: Int) {
    TERRIBLE("Terrivel", 1),
    BAD("Ruim", 2),
    AVERAGE("Médio",3),
    GOOD("Feliz", 4),
    EXCELLENT("Excelente", 5)
}