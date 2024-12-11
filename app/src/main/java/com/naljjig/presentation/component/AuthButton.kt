package com.naljjig.presentation.component

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.naljjig.core.designsystem.NaljjigTheme

@Composable
fun AuthButton(
    type: String,
    text: String
) {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .background(
                color = if(type == "primary") NaljjigTheme.colors.primaryButton else NaljjigTheme.colors.secondaryButton,
                shape = RoundedCornerShape(10.dp)
            )
            .padding(vertical = 15.dp),
    ) {
        Text(
            modifier = Modifier.align(Alignment.Center),
            text = text,
            fontSize = 20.sp,
            fontWeight = FontWeight(600),
            color = if(type == "primary") NaljjigTheme.colors.secondaryButton else NaljjigTheme.colors.defaultText
        )
    }
}