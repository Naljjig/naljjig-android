package com.naljjig.presentation.home.data

import java.time.LocalDate
import java.time.LocalDateTime
import java.time.temporal.ChronoUnit

data class Schedule(
    val eventName: String,
    val description: String,
    val startDateTime: LocalDateTime,
    val endDateTime: LocalDateTime?,
    val category: String
){
    val dateSet = if(endDateTime == null) setOf(startDateTime.toLocalDate()) else List<LocalDate>(ChronoUnit.DAYS.between(startDateTime, endDateTime).toInt()+1){ index ->
        startDateTime.plusDays(index.toLong()).toLocalDate()
    }.toSet()
}
