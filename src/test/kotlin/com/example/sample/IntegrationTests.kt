package com.example.sample

import com.example.sample.dto.NewSpecializedAreaDto
import com.example.sample.dto.SpecializedAreaDto
import com.example.sample.entity.SpecializedArea
import com.example.sample.repository.SpecializedAreaRepository
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.boot.test.web.client.TestRestTemplate
import org.springframework.boot.test.web.client.getForEntity
import org.springframework.http.HttpStatus

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class IntegrationTests(
    @Autowired val restTemplate: TestRestTemplate,
    @Autowired val repo: SpecializedAreaRepository
) {
    @BeforeEach
    fun setUp() {
        repo.deleteAll()
        repo.saveAll(
            listOf(
                SpecializedArea(name = "経営"),
                SpecializedArea(name = "管理")
            )
        )
    }

    @Test
    fun `should respond ping-pong`() {
        val entity = restTemplate.getForEntity<String>("/")
        assertThat(entity.statusCode).isEqualTo(HttpStatus.OK)
        assertThat(entity.body).contains("<h1>pong</h1>")
    }

    @Test
    fun `should respond specialized area list`() {
        val response = restTemplate.getForEntity<Array<SpecializedAreaDto>>("/api/specialized-areas")
        assertThat(response.statusCode).isEqualTo(HttpStatus.OK)

        val areas = response.body?.toList() ?: emptyList()
        assertThat(areas).hasSize(2)
        assertThat(areas[0].name).isEqualTo("経営")
        assertThat(areas[1].name).isEqualTo("管理")
    }

    @Test
    fun `should create a new specialized area`() {
        val response = restTemplate.postForEntity<SpecializedAreaDto>(
            "/api/specialized-areas",
            NewSpecializedAreaDto(name = "IT・エンジニア"),
            SpecializedAreaDto::class.java
        )
        assertThat(response.statusCode).isEqualTo(HttpStatus.CREATED)

        val area = requireNotNull(response.body) { "response body should not be null" }
        assertThat(area.name).isEqualTo("IT・エンジニア")
    }
}
