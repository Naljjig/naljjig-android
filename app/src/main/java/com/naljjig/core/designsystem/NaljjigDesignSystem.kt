package com.naljjig.core.designsystem

import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.Immutable
import androidx.compose.runtime.staticCompositionLocalOf
import androidx.compose.ui.graphics.Color

@Immutable
data class NaljjigColors(
    val activated: Color,
    val deactivated: Color,
    val defaultText: Color,
    val additionalText: Color,
    val primaryButton: Color,
    val secondaryButton: Color,
    val editTextBackground: Color,
    val placeholder: Color,
    val firstCategory: Color,
    val secondCategory: Color,
    val thirdCategory: Color,
    val firstCategoryBackground: Color,
    val secondCategoryBackground: Color,
    val thirdCategoryBackground: Color
)

val LocalNaljjigColors = staticCompositionLocalOf {
    NaljjigColors(
        activated = Color.Unspecified,
        deactivated = Color.Unspecified,
        defaultText = Color.Unspecified,
        additionalText = Color.Unspecified,
        primaryButton = Color.Unspecified,
        secondaryButton = Color.Unspecified,
        editTextBackground = Color.Unspecified,
        placeholder = Color.Unspecified,
        firstCategory = Color.Unspecified,
        secondCategory = Color.Unspecified,
        thirdCategory = Color.Unspecified,
        firstCategoryBackground = Color.Unspecified,
        secondCategoryBackground = Color.Unspecified,
        thirdCategoryBackground = Color.Unspecified
    )
}

@Composable
fun NaljjigTheme(
    content: @Composable () -> Unit
){
    val naljjigColors = NaljjigColors(
        activated = Color(0xffC18B8F),
        deactivated = Color(0xFF8F9BB3),
        defaultText = Color(0xFF222B45),
        additionalText = Color(0xFF735BF2),
        primaryButton = Color(0xBD3A4FB6),
        secondaryButton = Color.White,
        editTextBackground = Color(0xffF1F4FF),
        placeholder = Color(0xFF626262),
        firstCategory = Color(0xFF735BF2),
        secondCategory = Color(0xFF00B383),
        thirdCategory = Color(0xFF0095FF),
        firstCategoryBackground = Color(0x12735BF2),
        secondCategoryBackground = Color(0x1200B383),
        thirdCategoryBackground = Color(0x120095FF)
    )
    CompositionLocalProvider(LocalNaljjigColors provides naljjigColors){
        MaterialTheme(
            content = content
        )
    }
}

object NaljjigTheme{
    val colors: NaljjigColors
        @Composable
        get() = LocalNaljjigColors.current
}