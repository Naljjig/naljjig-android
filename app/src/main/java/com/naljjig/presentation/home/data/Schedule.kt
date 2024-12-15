package com.naljjig.presentation.home.data

import java.time.LocalDate
import java.time.LocalTime

data class Schedule(
    val eventName: String,
    val description: String,
    val date: LocalDate,
    val startTime: LocalTime,
    val endTime: LocalTime,
    val category: String
)

val scheduleList = listOf(
    Schedule(
        eventName = "맥주 축제",
        description = "대구 치맥 페스티벌 대구 친구들이랑 가기로함.",
        date = LocalDate.of(2024,12,20),
        startTime = LocalTime.of(17,30),
        endTime = LocalTime.of(22,30),
        category = "festival"
    ),
    Schedule(
        eventName = "헬스",
        description = "가슴, 등 1시간 30분하기",
        date = LocalDate.of(2024,12,19),
        startTime = LocalTime.of(17,30),
        endTime = LocalTime.of(19,30),
        category = "work out"
    ),
    Schedule(
        eventName = "SQLD 시험",
        description = "SQLD 시험",
        date = LocalDate.of(2024,12,19),
        startTime = LocalTime.of(9,30),
        endTime = LocalTime.of(12,0),
        category = "study"
    )
)
