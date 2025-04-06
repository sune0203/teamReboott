package com.reboott.linguatech.repository

import com.reboott.linguatech.entity.feature.Feature
import org.springframework.data.jpa.repository.JpaRepository

interface FeatureRepository : JpaRepository<Feature, Long>
