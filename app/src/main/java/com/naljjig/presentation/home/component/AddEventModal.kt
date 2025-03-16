package com.naljjig.presentation.home.component

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ModalBottomSheet
import androidx.compose.material3.SheetState
import androidx.compose.material3.SheetValue
import androidx.compose.material3.Text
import androidx.compose.material3.rememberModalBottomSheetState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Density
import androidx.compose.ui.unit.dp

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AddEventBottomSheet(
    isSheetOpen: Boolean,
    onDismissRequest: () -> Unit,
    sheetState: SheetState,
) {
    val eventTitle = remember{ mutableStateOf("")}
    val description = remember{ mutableStateOf("")}
    val startYear = remember{ mutableStateOf("")}
    val startMonth = remember{ mutableStateOf("")}
    val startDay = remember{ mutableStateOf("")}
    val startHour = remember{ mutableStateOf("")}
    val startMinute = remember{ mutableStateOf("")}
    val endYear = remember{ mutableStateOf("")}
    val endMonth = remember{ mutableStateOf("")}
    val endDay = remember{ mutableStateOf("")}
    val endHour = remember{ mutableStateOf("")}
    val endMinute = remember{ mutableStateOf("")}
    if (isSheetOpen) {
        ModalBottomSheet(
            onDismissRequest = onDismissRequest,
            sheetState = sheetState,
            modifier = Modifier
                .height(650.dp)
            ,
            dragHandle = null
        ) {
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .background(color = Color.White)
                    .padding(horizontal = 16.dp),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Text(
                    text = "Add New Event"
                )
                Row(
                    modifier = Modifier.fillMaxWidth()
                ){
                    EventTextField(
                        value = eventTitle.value,
                        onValueChange = {newValue -> eventTitle.value = newValue},
                        placeholder = "event name"
                    )
                }
            }
        }
    }
}




@OptIn(ExperimentalMaterial3Api::class)
@Preview
@Composable
private fun AddEventBottomSheetPreview() {
    AddEventBottomSheet(
        true,
        {},
        rememberModalBottomSheetState(skipPartiallyExpanded = false)
    )
}

