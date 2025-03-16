package com.naljjig.presentation.home.component

import androidx.compose.animation.animateColorAsState
import androidx.compose.animation.core.animateDpAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.onFocusChanged
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.naljjig.core.designsystem.NaljjigTheme

@Composable
fun EventTextField(
    value: String,
    onValueChange: (String) -> Unit,
    placeholder: String,
    modifier: Modifier = Modifier
){
    var isTextFieldFocused by remember{ mutableStateOf(false) }
    val borderColor by animateColorAsState(
        targetValue = if (isTextFieldFocused) NaljjigTheme.colors.defaultText else NaljjigTheme.colors.deactivated,
        animationSpec = tween(durationMillis = 500),
        label = ""
    )

    TextField(
        value = value,
        onValueChange = onValueChange,
        placeholder = { Text(placeholder) },
        modifier = modifier
            .onFocusChanged { focusState ->
                isTextFieldFocused = focusState.isFocused
            }.border(
                width = 2.dp,
                color = borderColor,
                shape = RoundedCornerShape(10.dp)
            ).background(
                color = Color.White,
                shape = RoundedCornerShape(10.dp)
            )
        ,
        colors = TextFieldDefaults.colors(
            unfocusedContainerColor = NaljjigTheme.colors.secondaryButton,
            focusedContainerColor = NaljjigTheme.colors.secondaryButton,
            focusedIndicatorColor = Color.Transparent,
            unfocusedIndicatorColor = Color.Transparent
        ),
        shape = RoundedCornerShape(10.dp),
    )
}

@Preview
@Composable
fun AuthTextFieldPreview(){
    val eventTitle = remember{ mutableStateOf("") }
    val isConfirmPasswordTextFieldFocused = remember{ mutableStateOf(false) }

    EventTextField(
        value = eventTitle.value,
        onValueChange = {newValue -> eventTitle.value = newValue },
        placeholder = "eventTitle"
    )
}