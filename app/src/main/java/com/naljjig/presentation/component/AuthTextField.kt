package com.naljjig.presentation.component

import androidx.compose.animation.animateColorAsState
import androidx.compose.animation.core.animateDpAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.onFocusChanged
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.naljjig.core.designsystem.NaljjigTheme

@Composable
fun AuthTextField(
    type: String,
    value: String,
    onValueChanged: (String) -> Unit,
    isTextFieldFocused: Boolean,
    onFocusedChange: (Boolean) -> Unit
) {
    val borderColor by animateColorAsState(
        targetValue = if (isTextFieldFocused) NaljjigTheme.colors.primaryButton else Color.Transparent,
        animationSpec = tween(durationMillis = 500),
        label = ""
    )
    val borderWidth by animateDpAsState(
        targetValue = if (isTextFieldFocused) 2.dp else 0.dp,
        animationSpec = tween(durationMillis = 500),
        label = ""
    )

    TextField(
        value = value,
        onValueChange = { newValue ->
            onValueChanged(newValue)
        },
        modifier = Modifier
            .fillMaxWidth()
            .onFocusChanged { focusState ->
                onFocusedChange(focusState.isFocused)
            }
            .border(
                width = borderWidth,
                color = borderColor,
                shape = RoundedCornerShape(10.dp)
            ),
        colors = TextFieldDefaults.colors(
            unfocusedContainerColor = NaljjigTheme.colors.editTextBackground,
            focusedContainerColor = NaljjigTheme.colors.editTextBackground,
            focusedIndicatorColor = Color.Transparent,
            unfocusedIndicatorColor = Color.Transparent
        ),
        shape = RoundedCornerShape(10.dp),
        placeholder = {
            Text(
                modifier = Modifier.padding(vertical = 4.dp).padding(start = 4.dp),
                text = type,
                fontSize = 16.sp,
                fontWeight = FontWeight(500)
            )
        }
    )
}