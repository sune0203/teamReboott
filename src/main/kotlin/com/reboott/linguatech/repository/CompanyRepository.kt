package com.reboott.linguatech.repository

import com.reboott.linguatech.entity.company.Company
import org.springframework.data.jpa.repository.JpaRepository

interface CompanyRepository : JpaRepository<Company, Long>
