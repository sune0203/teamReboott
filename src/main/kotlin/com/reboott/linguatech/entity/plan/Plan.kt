package com.reboott.linguatech.entity.plan

import com.reboott.linguatech.entity.feature.Feature
import jakarta.persistence.*

@Entity
data class Plan(
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long = 0,
    val name: String,
    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(
        name = "plan_features",
        joinColumns = [JoinColumn(name = "plan_id")],
        inverseJoinColumns = [JoinColumn(name = "feature_id")]
    )
    val features: List<Feature>
)
