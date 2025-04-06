package com.reboott.linguatech.dto

data class CreateFeatureRequest(
        val name: String,
        val limitAmount: Int,
        val creditPerUse: Int
)