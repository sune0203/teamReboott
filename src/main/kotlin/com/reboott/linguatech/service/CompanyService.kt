package com.reboott.linguatech.service

import com.reboott.linguatech.dto.AssignPlanRequest
import com.reboott.linguatech.dto.CreateCompanyRequest
import com.reboott.linguatech.entity.company.Company
import com.reboott.linguatech.repository.CompanyRepository
import com.reboott.linguatech.repository.PlanRepository
import org.springframework.stereotype.Service

@Service
class CompanyService(
    private val companyRepository: CompanyRepository,
    private val planRepository: PlanRepository
) {
    fun createCompany(request: CreateCompanyRequest): Company {
        val plan = planRepository.findById(request.currentPlanId)
                .orElseThrow { IllegalArgumentException("존재하지 않는 요금제 ID입니다.") }

        val company = Company(
                name = request.name,
                credit = request.credit,
                currentPlan = plan
        )

        return companyRepository.save(company)
    }
    fun assignPlan(companyId: Long, request: AssignPlanRequest): Company {
        val company = companyRepository.findById(companyId)
            .orElseThrow { IllegalArgumentException("회사를 찾을 수 없습니다") }
        val plan = planRepository.findById(request.planId)
            .orElseThrow { IllegalArgumentException("요금제를 찾을 수 없습니다") }
        company.currentPlan = plan
        return companyRepository.save(company)
    }
}
