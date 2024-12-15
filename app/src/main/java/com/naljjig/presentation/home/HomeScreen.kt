package com.naljjig.presentation.home

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.naljjig.core.designsystem.NaljjigTheme
import com.naljjig.presentation.home.component.HomeCalendar

@Composable
fun HomeScreen(){
    Column(
        modifier = Modifier.fillMaxSize()
    ) {
        Spacer(modifier = Modifier.height(20.dp))

        HomeCalendar()
    }
}

@Composable
@Preview
fun HomeScreenPreview(){
    NaljjigTheme {
        HomeScreen()
    }
}