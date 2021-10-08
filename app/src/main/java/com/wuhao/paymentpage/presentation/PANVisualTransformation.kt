package com.wuhao.paymentpage.presentation

import androidx.compose.ui.text.AnnotatedString
import androidx.compose.ui.text.input.OffsetMapping
import androidx.compose.ui.text.input.TransformedText
import androidx.compose.ui.text.input.VisualTransformation

class PANVisualTransformation : VisualTransformation {
    override fun filter(text: AnnotatedString): TransformedText {

        val trimmed = if (text.text.length >= 16) text.text.substring(0..15) else text.text
        var output = ""
        for (i in trimmed.indices) {
            if (i in 4..11) {
                output += "*"
            } else {
                output += trimmed[i]
            }
            if (i % 4 == 3 && i != 15) output += "-"
        }

        val numberTranslator = object : OffsetMapping {
            override fun originalToTransformed(offset: Int): Int {
                if (offset <= 3) return offset
                if (offset <= 7) return offset + 1
                if (offset <= 11) return offset + 2
                if (offset <= 15) return offset + 3
                return 19
            }

            override fun transformedToOriginal(offset: Int): Int {

                if (offset <= 3) return offset
                if (offset <= 7) return offset - 1
                if (offset <= 11) return offset - 2
                if (offset <= 15) return offset - 3
                return 16

            }

        }

        return TransformedText(
            AnnotatedString(output),
            numberTranslator
        )

    }

}