package com.naljjig.presentation.home

import android.content.Context
import android.graphics.Bitmap
import android.graphics.BitmapFactory
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
import androidx.compose.runtime.LaunchedEffect
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
import com.google.ai.client.generativeai.GenerativeModel
import com.google.ai.client.generativeai.type.content
import com.naljjig.R
import com.naljjig.core.designsystem.NaljjigTheme
import com.naljjig.presentation.home.component.AddEventBottomSheet
import com.naljjig.presentation.home.component.HomeCalendar
import com.naljjig.presentation.home.data.Schedule
import com.naljjig.presentation.home.data.parseScheduleResponse
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomeScreen(){
    var selectedImageBitmap by remember { mutableStateOf<Bitmap?>(null)}
    val context = LocalContext.current

    val scheduleList = remember{ mutableStateOf( listOf(
        Schedule(
            eventName = "맥주 축제",
            description = "대구 치맥 페스티벌",
            startDateTime = LocalDateTime.of(2024,12,20, 9,30),
            endDateTime = LocalDateTime.of(2024,12,22,17,30),
            category = "festival"
        ),
        Schedule(
            eventName = "헬스",
            description = "가슴, 등 1시간 30분하기",
            startDateTime = LocalDateTime.of(2024,12,19,17,30),
            endDateTime = LocalDateTime.of(2024,12,19,19,30),
            category = "work out"
        ),
        Schedule(
            eventName = "SQLD 시험",
            description = "SQLD 시험",
            startDateTime = LocalDateTime.of(2024,12,19,9,30),
            endDateTime = LocalDateTime.of(2024,12,30,12,0),
            category = "study"
        ),
        Schedule(
            eventName = "눈싸움",
            description = "크리스마스 기념 눈싸움",
            startDateTime = LocalDateTime.of(2024,12,25,9,30),
            endDateTime = null,
            category = "fight"
        )
    )
    )
    }

    val albumLauncher = rememberLauncherForActivityResult(
        contract = ActivityResultContracts.GetContent(),
    ) { uri ->
        selectedImageBitmap = uri?.let { uriToBitmap(context, it) }
    }



    LaunchedEffect(selectedImageBitmap){
        if(selectedImageBitmap!=null) {
            val generativeModel = GenerativeModel(
                // The Gemini 1.5 models are versatile and work with most use cases
                modelName = "gemini-1.5-flash",
                // Access your API key as a Build Configuration variable (see "Set up your API key" above)
                apiKey = "AIzaSyCEtG0v1gxLCykmxscNwFat0rgeWb990NA"
            )

            val inputContent = content {
                selectedImageBitmap?.let { image(it) }
                text("""
                     이 이미지를 분석하여 일정에 기록할 수 있도록 일정 제목,간략한 일정 설명, 날짜, 시간, 카테고리와 같은 일정 정보를 JSON 형식으로 추출해주세요.
        반환되는 날짜는 반드시 "YYYY년MM월DD일" 형식으로, 시간은 "HH:MM" (24시간제) 형식으로 통일해주세요.
        만약 날짜나 시간 정보가 명확하지 않다면, 해당 필드는 null로 설정해주세요.
        여러 날짜와 시간이 나타난다면, 가장 이른 날짜와 시간을 기준으로 표시해주세요.
        만약에 start_time은 년도가 없으면 2024로 채워야하고, 시간이 없으면 12:00으로 꼭 채워야해요.
        날짜와 시간 사이는 꼭 한칸 띄우기!
        그래서 내가 받은 JSON을 

        JSON 형식 예시:
        {       
          "schedule_info": {
            "event_title": "이벤트 제목",
            "description" : "간략한 일정 설명"
            "start_time": "2024년12월25 14:00",
            "end_time": "2024년12월25 19:00",
            "category": "카테고리"
          }
        }
                """.trimIndent())
            }
            val generatedResponse = generativeModel.generateContent(inputContent)
            val response = generatedResponse.text.toString()
            Log.d("zz", response)
            val scheduleResponse = parseScheduleResponse(
                response
                .removePrefix("```json")
                .trim()
                .removeSuffix("```")
                .trim()
            )
            val formatter = DateTimeFormatter.ofPattern("yyyy년MM월dd일 HH:mm")
            val schedule =
                Schedule(
                    eventName = scheduleResponse.schedule_info.event_title,
                    description = scheduleResponse.schedule_info.description,
                    startDateTime = LocalDateTime.parse(scheduleResponse.schedule_info.start_time, formatter),
                    endDateTime = if(scheduleResponse.schedule_info.end_time == null) null else LocalDateTime.parse(scheduleResponse.schedule_info.end_time,formatter),
                    category = scheduleResponse.schedule_info.category
                )

            val updatedList = scheduleList.value.toMutableList().apply {
                add(schedule)
            }
            scheduleList.value = updatedList
            Log.d("zz", schedule.toString())
            Log.d("zz", scheduleResponse.schedule_info.toString())
            Log.d("zz", scheduleList.value.toString())
        }
    }


    val isSheetOpen = remember { mutableStateOf(false) }

    Box {
        LazyColumn(
            modifier = Modifier.fillMaxSize()
        ) {
            item{Spacer(modifier = Modifier.height(20.dp))}

            item{HomeCalendar(
                scheduleList.value
            )}

            item{Spacer(modifier = Modifier.height(80.dp))}
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

fun uriToBitmap(context: Context, uri: Uri): Bitmap? {
    return try {
        val inputStream = context.contentResolver.openInputStream(uri)
        BitmapFactory.decodeStream(inputStream).also {
            inputStream?.close()
        }
    } catch (e: Exception) {
        e.printStackTrace()
        null
    }
}