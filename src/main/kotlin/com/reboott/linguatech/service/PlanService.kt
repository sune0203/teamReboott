package com.reboott.linguatech.service

import com.reboott.linguatech.dto.CreatePlanRequest
import com.reboott.linguatech.entity.feature.Feature
import com.reboott.linguatech.entity.plan.Plan
import com.reboott.linguatech.repository.FeatureRepository
import com.reboott.linguatech.repository.PlanRepository
import org.springframework.stereotype.Service

@Service
class PlanService(
    private val planRepository: PlanRepository,
    private val featureRepository: FeatureRepository
) {

    fun createPlan(request: CreatePlanRequest): Plan {
        val features: List<Feature> = featureRepository.findAllById(request.featureIds)
        val plan = Plan(name = request.name, features = features)
        return planRepository.save(plan)
    }
}
