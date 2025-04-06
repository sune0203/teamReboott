package com.reboott.linguatech.dto

data class FeatureUseRequest(
    val companyId: Long,
    val featureId: Long,
    val usageAmount: Int
)
