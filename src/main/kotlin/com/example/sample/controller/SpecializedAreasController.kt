package com.example.sample.controller

import com.example.sample.dto.SpecializedAreaDto
import com.example.sample.repository.SpecializedAreaRepository
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RestController

@RestController
class SpecializedAreasController(
  private val repo: SpecializedAreaRepository
) {
  @GetMapping("/api/specialized-areas")
  fun getSpecializedAreas(): List<SpecializedAreaDto> {
//    val specializedAreas = arrayOf(
//      SpecializedAreaDto(id = 1, name = "経営"),
//      SpecializedAreaDto(id = 2, name = "管理"),
//      SpecializedAreaDto(id = 3, name = "コンサルタント"),
//    )
    return repo.findSpecializedAreas()
  }
}