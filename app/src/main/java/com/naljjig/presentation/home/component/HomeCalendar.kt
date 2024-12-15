package com.naljjig.presentation.home.component

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.ArrowForward
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.naljjig.core.designsystem.NaljjigTheme
import com.naljjig.presentation.home.data.Schedule
import com.naljjig.presentation.home.data.scheduleList
import java.time.LocalDate
import java.time.LocalDateTime

@Composable
fun HomeCalendar(){
    val selectedDate = remember { mutableStateOf( LocalDate.now()) }
    val currentMonth = remember { mutableStateOf(selectedDate.value.withDayOfMonth(1))}
    val daysInMonth = currentMonth.value.lengthOfMonth()
    val firstDayOfWeek = currentMonth.value.dayOfWeek.value % 7// 0 = Sunday
    val totalSlots = daysInMonth + firstDayOfWeek

    val categoryToColor = mapOf(
        "work out" to NaljjigTheme.colors.firstCategory,
        "festival" to NaljjigTheme.colors.secondCategory,
        "study" to NaljjigTheme.colors.thirdCategory
    )

    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 18.dp)
    ) {
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            IconButton(onClick = { currentMonth.value = currentMonth.value.minusMonths(1) }) {
                Icon(Icons.Default.ArrowBack, contentDescription = "Previous")
            }
            Column(
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Text(
                    text = "${currentMonth.value.month}"
                )
                Text(
                    text = "${currentMonth.value.year}"
                )
            }
            IconButton(onClick = { currentMonth.value = currentMonth.value.plusMonths(1) }) {
                Icon(Icons.Default.ArrowForward, contentDescription = "Next")
            }
        }

        Spacer(modifier = Modifier.height(16.dp))

        // Days of the week
        Row(Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.SpaceEvenly) {
            listOf("Sun","Mon", "Tue", "Wed", "Thu", "Fri", "Sat").forEach { day ->
                Text(text = day)
            }
        }

        Spacer(modifier = Modifier.height(8.dp))

        LazyVerticalGrid(
            columns = GridCells.Fixed(7),
            modifier = Modifier
                .fillMaxWidth()
                .height(200.dp),
            verticalArrangement = Arrangement.spacedBy(4.dp),
            userScrollEnabled = false
        ) {
            items(firstDayOfWeek) {
                Box(modifier = Modifier.padding(vertical = 5.dp))
            }

            items(daysInMonth) { day ->
                val date = currentMonth.value.plusDays(day.toLong())
                Box(
                    modifier = Modifier
                        .clickable { selectedDate.value = date }
                        .padding(horizontal = 5.dp)
                        .background(
                            if (date == selectedDate.value) NaljjigTheme.colors.activated else Color.Transparent,
                            shape = RoundedCornerShape(10.dp)
                        )
                        .padding(vertical = 5.dp),
                    contentAlignment = Alignment.Center
                ) {
                    Text(
                        text = (day+1).toString(),
                        textAlign = TextAlign.Center,
                        color = if (date == selectedDate.value) NaljjigTheme.colors.secondaryButton else NaljjigTheme.colors.defaultText
                    )
                    Row(
                        modifier = Modifier
                            .fillMaxWidth(0.5f)
                            .align(Alignment.BottomCenter)
                        ,
                        horizontalArrangement = Arrangement.Center
                    ) {
                        scheduleList.filter { it.dateSet.contains(date) }.forEachIndexed {index, schedule ->
                            if(index != 0) Spacer(modifier = Modifier.width(4.dp))
                            Box(
                                modifier = Modifier
                                    .size(4.dp)
                                    .border(
                                        width = 1.dp,
                                        color = (if(categoryToColor[schedule.category] ==null) NaljjigTheme.colors.deactivated else categoryToColor[schedule.category])!!,
                                        shape = CircleShape
                                    )
                                    .align(Alignment.Bottom)
                            ){}
                        }
                    }
                }
            }
        }

        Spacer(modifier = Modifier.height(36.dp))

        scheduleList.filter { it.dateSet.contains(selectedDate.value) }.forEachIndexed { index, schedule ->
            if(index != 0) Spacer(modifier = Modifier.height(16.dp))
            ScheduleListItem(schedule)
        }
    }
}

@Composable
fun ScheduleListItem(
    schedule: Schedule
){
    val categoryToColor = mapOf(
        "work out" to NaljjigTheme.colors.firstCategory,
        "festival" to NaljjigTheme.colors.secondCategory,
        "study" to NaljjigTheme.colors.thirdCategory
    )

    Column(
        modifier = Modifier
            .fillMaxWidth()
            .background(
                color = NaljjigTheme.colors.secondaryButton,
                shape = RoundedCornerShape(10.dp)
            )
            .padding(horizontal = 12.dp, vertical = 8.dp),
        verticalArrangement = Arrangement.spacedBy(8.dp)
    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically
        ){
            Box(
                modifier = Modifier
                    .size(9.dp)
                    .border(
                        width = 2.dp,
                        color = (if(categoryToColor[schedule.category] ==null) NaljjigTheme.colors.deactivated else categoryToColor[schedule.category])!!,
                        shape = CircleShape
                    )
            )

            Spacer(modifier = Modifier.width(14.dp))

            Text(
                text = dateToString(schedule.startDateTime) + " ~ " + dateToString(schedule.endDateTime),
                fontSize = 12.sp,
                fontWeight = FontWeight.Normal,
                color = NaljjigTheme.colors.deactivated
            )
        }
        
        Text(
            text = schedule.eventName,
            fontSize = 16.sp,
            fontWeight = FontWeight.Normal,
            color = NaljjigTheme.colors.defaultText
        )

        Text(
            text = schedule.description,
            fontSize = 12.sp,
            fontWeight = FontWeight.Normal,
            color = NaljjigTheme.colors.deactivated
        )
    }
}

@Preview(showBackground = true)
@Composable
fun ScheduleListItemPreview(){
    ScheduleListItem(
        schedule = Schedule(
            eventName = "맥주 축제",
            description = "대구 치맥 페스티벌",
            startDateTime = LocalDateTime.of(2024,12,20, 2017,30),
            endDateTime = LocalDateTime.of(2024,12,22,17,30),
            category = "festival"
        )
    )
}

@Composable
@Preview
fun HomeCalendarPreview(){
    NaljjigTheme {
        HomeCalendar()
    }
}

fun dateToString(startDateTime: LocalDateTime): String{
    return startDateTime.year.toString() + "년" +
            startDateTime.month.value.toString() + "월" +
            startDateTime.dayOfMonth.toString() + "일 " +
            startDateTime.hour.toString().padStart(2,'0') + ":" +
            startDateTime.minute.toString().padStart(2,'0')
}