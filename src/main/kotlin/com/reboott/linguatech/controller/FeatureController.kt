package com.reboott.linguatech.controller

import com.reboott.linguatech.dto.FeatureUseRequest
import com.reboott.linguatech.dto.UsageStatsResponse
import com.reboott.linguatech.entity.usage.FeatureUsage
import com.reboott.linguatech.service.FeatureService
import com.reboott.linguatech.common.DefaultRes
import com.reboott.linguatech.common.StatusCode
import com.reboott.linguatech.common.ResponseMessage
import com.reboott.linguatech.dto.CreateFeatureRequest
import com.reboott.linguatech.entity.feature.Feature
import org.springframework.format.annotation.DateTimeFormat
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*
import java.time.LocalDateTime

@RestController
@RequestMapping("/features")
class FeatureController(
        private val featureService: FeatureService
) {


    @PostMapping
    fun createFeature(@RequestBody request: CreateFeatureRequest): ResponseEntity<DefaultRes<Feature>> {
        val created = featureService.createFeature(request)
        return ResponseEntity.ok(
                DefaultRes.res(StatusCode.CREATED, ResponseMessage.CREATED, created)
        )
    }

    @PostMapping("/use")
    fun useFeature(@RequestBody request: FeatureUseRequest): ResponseEntity<DefaultRes<FeatureUsage>> {
        val result = featureService.useFeature(request)
        return ResponseEntity.ok(DefaultRes.res(StatusCode.OK, ResponseMessage.SUCCESS, result))
    }

    @GetMapping("/usage/statistics")
    fun usageStatistics(
            @RequestParam companyId: Long,
            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) from: LocalDateTime,
            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) to: LocalDateTime
    ): ResponseEntity<DefaultRes<List<UsageStatsResponse>>> {
        val stats = featureService.getUsageStats(companyId, from, to)
        return ResponseEntity.ok(DefaultRes.res(StatusCode.OK, ResponseMessage.SUCCESS, stats))
    }
}
