package com.reboott.linguatech.entity.company

import com.reboott.linguatech.entity.plan.Plan
import jakarta.persistence.*

@Entity
data class Company(
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long = 0,
    val name: String,
    var credit: Int,
    @ManyToOne var currentPlan: Plan?
)
