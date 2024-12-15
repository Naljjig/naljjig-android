package com.naljjig.presentation.home

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.naljjig.R
import com.naljjig.core.designsystem.NaljjigTheme
import com.naljjig.presentation.home.component.HomeCalendar

@Composable
fun HomeScreen(){
    Box {
        LazyColumn(
            modifier = Modifier.fillMaxSize()
        ) {
            item{Spacer(modifier = Modifier.height(20.dp))}

            item{HomeCalendar()}
        }

        Row(
            modifier = Modifier
                .fillMaxWidth(5/7f)
                .align(Alignment.BottomCenter)
                .padding(bottom = 60.dp),
            horizontalArrangement = Arrangement.SpaceAround
        ) {
            Row(
                modifier = Modifier
                    .weight(2/5f)
                    .background(
                        color = NaljjigTheme.colors.activated,
                        shape = RoundedCornerShape(10.dp)
                    )
                    .padding(4.dp),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.Center
            ) {
                Icon(
                    imageVector = ImageVector.vectorResource(R.drawable.ic_camera),
                    contentDescription = "",
                    tint = Color.Unspecified
                )

                Spacer(modifier = Modifier.width(4.dp))

                Text(
                    text = "이미지 추가",
                    color = NaljjigTheme.colors.secondaryButton
                )
            }

            Spacer(modifier = Modifier.weight(1/5f))

            Row(
                modifier = Modifier
                    .weight(2/5f)
                    .background(
                        color = NaljjigTheme.colors.activated,
                        shape = RoundedCornerShape(10.dp)
                    )
                    .padding(4.dp),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.Center
            ) {
                Icon(
                    imageVector = ImageVector.vectorResource(R.drawable.ic_edit),
                    contentDescription = "",
                    tint = Color.Unspecified
                )

                Spacer(modifier = Modifier.width(4.dp))

                Text(
                    text = "일정 추가",
                    color = NaljjigTheme.colors.secondaryButton
                )
            }
        }
    }
}

@Composable
@Preview
fun HomeScreenPreview(){
    NaljjigTheme {
        HomeScreen()
    }
}