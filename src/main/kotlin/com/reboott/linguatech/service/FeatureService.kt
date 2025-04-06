package com.reboott.linguatech.service

import com.reboott.linguatech.dto.CreateFeatureRequest
import com.reboott.linguatech.dto.FeatureUseRequest
import com.reboott.linguatech.dto.UsageStatsResponse
import com.reboott.linguatech.entity.feature.Feature
import com.reboott.linguatech.entity.usage.FeatureUsage
import com.reboott.linguatech.repository.CompanyRepository
import com.reboott.linguatech.repository.FeatureRepository
import com.reboott.linguatech.repository.FeatureUsageRepository
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional
import java.time.LocalDateTime

@Service
class FeatureService(
    private val companyRepository: CompanyRepository,
    private val featureRepository: FeatureRepository,
    private val featureUsageRepository: FeatureUsageRepository
) {

    fun createFeature(request: CreateFeatureRequest): Feature {

        if (request.limitAmount <= 0 || request.creditPerUse <= 0) {
            throw IllegalArgumentException("limitAmount와 creditPerUse는 0보다 커야 합니다.")
        }

        val feature = Feature(
                name = request.name,
                limitAmount = request.limitAmount,
                creditPerUse = request.creditPerUse
        )
        return featureRepository.save(feature)
    }

    @Transactional
    fun useFeature(request: FeatureUseRequest): FeatureUsage {
        val company = companyRepository.findById(request.companyId)
                .orElseThrow { IllegalArgumentException("회사를 찾을 수 없습니다") }
        val feature = featureRepository.findById(request.featureId)
                .orElseThrow { IllegalArgumentException("기능을 찾을 수 없습니다") }

        // 💥 요금제 기능 제한 체크 추가
        val currentPlan = company.currentPlan
                ?: throw IllegalArgumentException("회사가 요금제를 할당받지 않았습니다")
        val allowedFeatures = currentPlan.features.map { it.id }
        if (feature.id !in allowedFeatures) {
            throw IllegalArgumentException("이 기능은 현재 요금제에서 사용할 수 없습니다")
        }

        if (request.usageAmount > feature.limitAmount) {
            throw IllegalArgumentException("사용량이 기능 제한을 초과했습니다.")
        }

        val totalCost = feature.creditPerUse * request.usageAmount
        if (company.credit < totalCost) {
            throw IllegalArgumentException("크레딧이 부족합니다")
        }

        company.credit -= totalCost
        val usage = FeatureUsage(
                company = company,
                feature = feature,
                usedAmount = request.usageAmount,
                deductedCredits = totalCost,
                usedAt = LocalDateTime.now()
        )

        featureUsageRepository.save(usage)
        return usage
    }


    fun getUsageStats(companyId: Long, from: LocalDateTime, to: LocalDateTime): List<UsageStatsResponse> {
        val usages = featureUsageRepository.findAllByCompanyIdAndUsedAtBetween(companyId, from, to)
        return usages.groupBy { it.feature.name }.map { (featureName, usageList) ->
            UsageStatsResponse(
                featureName = featureName,
                totalUsed = usageList.sumOf { it.usedAmount },
                totalCredits = usageList.sumOf { it.deductedCredits },
                lastUsed = usageList.maxByOrNull { it.usedAt }?.usedAt ?: from
            )
        }
    }

}
