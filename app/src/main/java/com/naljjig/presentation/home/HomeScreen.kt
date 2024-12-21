package com.naljjig.presentation.home

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.rememberModalBottomSheetState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.naljjig.core.designsystem.NaljjigTheme
import com.naljjig.presentation.home.component.AddEventBottomSheet
import com.naljjig.presentation.home.component.HomeCalendar

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomeScreen(){
    Column(
        modifier = Modifier.fillMaxSize()
    ) {
        val isSheetOpen = remember { mutableStateOf(true) }
        Spacer(modifier = Modifier.height(20.dp))

        HomeCalendar()

        AddEventBottomSheet(
            isSheetOpen = isSheetOpen.value,
            onDismissRequest = { isSheetOpen.value = !isSheetOpen.value },
            sheetState = rememberModalBottomSheetState(skipPartiallyExpanded = true)
        )
    }
}

@Composable
@Preview
fun HomeScreenPreview(){
    NaljjigTheme {
        HomeScreen()
    }
}