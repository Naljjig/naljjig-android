package com.naljjig.presentation.home.data

import kotlinx.serialization.Serializable
import kotlinx.serialization.json.Json

@Serializable
data class ScheduleResponse(
    val schedule_info: ScheduleInfo
)

@Serializable
data class ScheduleInfo(
    val event_title: String,
    val description: String,
    val start_time: String,
    val end_time: String?,
    val category: String
)

fun parseScheduleResponse(json: String): ScheduleResponse {
    return Json.decodeFromString(ScheduleResponse.serializer(), json)
}
