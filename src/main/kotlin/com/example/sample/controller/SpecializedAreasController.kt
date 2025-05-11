package com.example.sample.controller

import com.example.sample.dto.NewSpecializedAreaDto
import com.example.sample.dto.SpecializedAreaDto
import com.example.sample.repository.SpecializedAreaRepository
import com.example.sample.service.SpecializedAreaService
import org.springframework.http.HttpStatus
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.ResponseStatus
import org.springframework.web.bind.annotation.RestController

@RestController
class SpecializedAreasController(
  private val repo: SpecializedAreaRepository,
  private val service: SpecializedAreaService
) {
  @GetMapping("/api/specialized-areas")
  fun getSpecializedAreas(): List<SpecializedAreaDto> {
    return repo.findSpecializedAreas()
  }

  @PostMapping("/api/specialized-areas")
  @ResponseStatus(HttpStatus.CREATED)
  fun createSpecializedArea(
    @RequestBody req: NewSpecializedAreaDto
  ): SpecializedAreaDto {
    val area = service.createSpecializedArea(req.name)
    val id = requireNotNull(area.id) { "id should not be blank." }
    return SpecializedAreaDto(id = id, name = area.name)
  }
}
