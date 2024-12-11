package com.naljjig.presentation.component

import androidx.annotation.DrawableRes
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.unit.dp

@Composable
fun SnsButton(
    @DrawableRes icon: Int,
    contentDescription: String
) {
    Box(
        modifier = Modifier
            .background(
                color = Color(0xFFECECEC),
                shape = RoundedCornerShape(10.dp)
            )
    ) {
        Icon(
            modifier = Modifier
                .padding(horizontal = 18.dp, vertical = 10.dp),
            imageVector = ImageVector.vectorResource(icon),
            contentDescription = contentDescription
        )
    }
}