package com.emodiario.presentation.common

import androidx.compose.ui.text.AnnotatedString
import androidx.compose.ui.text.input.OffsetMapping
import androidx.compose.ui.text.input.TransformedText
import androidx.compose.ui.text.input.VisualTransformation

class PhoneMask : VisualTransformation {
    override fun filter(text: AnnotatedString): TransformedText {
        val mask = text.text.mapIndexed { index, c ->
            when (index) {
                0 -> "($c"
                1 -> "$c)"
                7 -> "-$c"
                else -> c
            }
        }.joinToString(separator = "")

        return TransformedText(AnnotatedString(mask), PhoneOffsetMapping)
    }

    object PhoneOffsetMapping : OffsetMapping {
        override fun originalToTransformed(offset: Int): Int {
            return when{
                offset > 7 -> offset + 3
                offset > 1 -> offset + 2
                offset > 0 -> offset + 1
                else -> offset
            }
        }

        override fun transformedToOriginal(offset: Int): Int {
            return when{
                offset > 5 -> offset - 3
                offset > 1 -> offset - 2
                else -> offset
            }
        }
    }

}
