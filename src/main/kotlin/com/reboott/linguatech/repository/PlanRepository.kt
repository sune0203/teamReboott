package com.reboott.linguatech.repository

import com.reboott.linguatech.entity.plan.Plan
import org.springframework.data.jpa.repository.JpaRepository

interface PlanRepository : JpaRepository<Plan, Long>
