package com.naljjig.presentation.home.component

import androidx.compose.foundation.layout.height
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ModalBottomSheet
import androidx.compose.material3.SheetState
import androidx.compose.material3.SheetValue
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
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
    if (isSheetOpen) {
        ModalBottomSheet(
            onDismissRequest = onDismissRequest,
            sheetState = sheetState,
            modifier = Modifier
                .height(650.dp),
            dragHandle = null
        ) {}
    }
}
