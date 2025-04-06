package com.reboott.linguatech.dto

data class CreateCompanyRequest(
        val name: String,
        val credit: Int,
        val currentPlanId: Long
)
