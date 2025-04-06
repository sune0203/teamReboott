package com.reboott.linguatech.controller

import com.reboott.linguatech.dto.CreatePlanRequest
import com.reboott.linguatech.entity.plan.Plan
import com.reboott.linguatech.service.PlanService
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*
import com.reboott.linguatech.common.DefaultRes
import com.reboott.linguatech.common.StatusCode
import com.reboott.linguatech.common.ResponseMessage

@RestController
@RequestMapping("/plans")
class PlanController(
    private val planService: PlanService
) {

    @PostMapping
    fun createPlan(@RequestBody request: CreatePlanRequest): ResponseEntity<DefaultRes<Plan>> {
        val created = planService.createPlan(request)
        return ResponseEntity.ok(DefaultRes.res(StatusCode.CREATED, ResponseMessage.CREATED, created))
    }
}
