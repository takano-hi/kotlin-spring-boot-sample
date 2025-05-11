package com.example.sample.repository

import com.example.sample.dto.SpecializedAreaDto
import com.example.sample.entity.SpecializedArea
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.Query

interface SpecializedAreaRepository : JpaRepository<SpecializedArea, Int> {
  @Query("""
    SELECT new com.example.sample.dto.SpecializedAreaDto(a.id, a.name)
    FROM SpecializedArea a
    ORDER BY a.id ASC
  """)
  fun findSpecializedAreas(): List<SpecializedAreaDto>
}