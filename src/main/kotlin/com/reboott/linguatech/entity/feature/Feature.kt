package com.reboott.linguatech.entity.feature

import jakarta.persistence.*

@Entity
data class Feature(
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long = 0,
    val name: String,
    val limitAmount: Int,
    val creditPerUse: Int
)
