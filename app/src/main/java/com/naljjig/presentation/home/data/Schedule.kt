package com.naljjig.presentation.home.data

import java.time.LocalDate
import java.time.LocalDateTime
import java.time.temporal.ChronoUnit

data class Schedule(
    val eventName: String,
    val description: String,
    val startDateTime: LocalDateTime,
    val endDateTime: LocalDateTime,
    val category: String
){
    val dateSet = List<LocalDate>(ChronoUnit.DAYS.between(startDateTime, endDateTime).toInt()+1){ index ->
        startDateTime.plusDays(index.toLong()).toLocalDate()
    }.toSet()
}

val scheduleList = listOf(
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
    )
)
