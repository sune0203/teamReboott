package com.reboott.linguatech.repository

import com.reboott.linguatech.entity.usage.FeatureUsage
import org.springframework.data.jpa.repository.JpaRepository
import java.time.LocalDateTime

interface FeatureUsageRepository : JpaRepository<FeatureUsage, Long> {
    fun findAllByCompanyIdAndUsedAtBetween(
        companyId: Long,
        from: LocalDateTime,
        to: LocalDateTime
    ): List<FeatureUsage>
}
