package com.reboott.linguatech.dto

import java.time.LocalDateTime

data class UsageStatsResponse(
    val featureName: String,
    val totalUsed: Int,
    val totalCredits: Int,
    val lastUsed: LocalDateTime
)
