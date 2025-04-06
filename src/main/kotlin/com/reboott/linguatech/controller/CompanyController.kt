package com.reboott.linguatech.controller

import com.reboott.linguatech.dto.AssignPlanRequest
import com.reboott.linguatech.entity.company.Company
import com.reboott.linguatech.service.CompanyService
import com.reboott.linguatech.common.DefaultRes
import com.reboott.linguatech.common.StatusCode
import com.reboott.linguatech.common.ResponseMessage
import com.reboott.linguatech.dto.CreateCompanyRequest
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/companies")
class CompanyController(
        private val companyService: CompanyService
) {

    @PostMapping
    fun createCompany(@RequestBody request: CreateCompanyRequest): ResponseEntity<DefaultRes<Company>> {
        val created = companyService.createCompany(request)
        return ResponseEntity.ok(
                DefaultRes.res(StatusCode.CREATED, ResponseMessage.CREATED, created)
        )
    }

    @PostMapping("/{companyId}/assign-plan")
    fun assignPlan(
            @PathVariable companyId: Long,
            @RequestBody request: AssignPlanRequest
    ): ResponseEntity<DefaultRes<Company>> {
        val updated = companyService.assignPlan(companyId, request)
        return ResponseEntity.ok(DefaultRes.res(StatusCode.OK, ResponseMessage.SUCCESS, updated))
    }

}
