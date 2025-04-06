package com.reboott.linguatech.dto

data class CreatePlanRequest(
    val name: String,
    val featureIds: List<Long>
)
