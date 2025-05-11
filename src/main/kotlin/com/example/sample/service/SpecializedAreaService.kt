package com.example.sample.service

import com.example.sample.entity.SpecializedArea
import jakarta.persistence.EntityManager
import jakarta.transaction.Transactional
import org.springframework.stereotype.Service

@Service
class SpecializedAreaService(
  private val em: EntityManager
) {
  @Transactional
  fun createSpecializedArea(name: String): SpecializedArea {
    val area = SpecializedArea(
      name = name
    )

    em.persist(area)

    return area
  }
}