package com.reboott.linguatech.entity.usage

import com.reboott.linguatech.entity.company.Company
import com.reboott.linguatech.entity.feature.Feature
import jakarta.persistence.*
import java.time.LocalDateTime

@Entity
data class FeatureUsage(
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long = 0,
    @ManyToOne val company: Company,
    @ManyToOne val feature: Feature,
    val usedAmount: Int,
    val deductedCredits: Int,
    val usedAt: LocalDateTime
)
