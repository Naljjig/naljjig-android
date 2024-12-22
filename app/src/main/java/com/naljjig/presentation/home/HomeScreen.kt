package com.naljjig.presentation.home

import android.content.Context
import android.net.Uri
import android.util.Log
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.material3.rememberModalBottomSheetState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.naljjig.R
import com.naljjig.core.designsystem.NaljjigTheme
import com.naljjig.presentation.home.component.AddEventBottomSheet
import com.naljjig.presentation.home.component.HomeCalendar

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomeScreen(){
    var selectedImageUri by remember { mutableStateOf<Uri?>(null) }
    var selectedImagePath by remember { mutableStateOf("") }
    val context = LocalContext.current


    val albumLauncher = rememberLauncherForActivityResult(
        contract = ActivityResultContracts.GetContent(),
    ) { uri ->
        selectedImageUri = uri
        if (uri != null) {
            selectedImagePath = getPathFromUri(context,uri)
        }
        Log.d("zz",selectedImagePath)
    }
    val isSheetOpen = remember { mutableStateOf(false) }

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
                    .padding(4.dp)
                    .clickable {
                        albumLauncher.launch("image/*")
                    },
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
                    .clickable {
                        isSheetOpen.value = !isSheetOpen.value
                    }
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
    AddEventBottomSheet(
        isSheetOpen = isSheetOpen.value,
        onDismissRequest = { isSheetOpen.value = !isSheetOpen.value },
        sheetState = rememberModalBottomSheetState(skipPartiallyExpanded = false)
    )
}

@Composable
@Preview
fun HomeScreenPreview(){
    NaljjigTheme {
        HomeScreen()
    }
}

// Helper to get file path from URI
fun getPathFromUri(context: Context, uri: Uri): String {
    val cursor = context.contentResolver.query(uri, null, null, null, null)
    return cursor?.let {
        val columnIndex = it.getColumnIndex("_data")
        it.moveToFirst()
        it.getString(columnIndex)
    } ?: throw IllegalArgumentException("Invalid URI")
}