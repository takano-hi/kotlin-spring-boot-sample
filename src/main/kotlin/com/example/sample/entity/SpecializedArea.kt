package com.example.sample.entity

import jakarta.persistence.*

@Entity
@Table(name = "specialized_areas")
class SpecializedArea {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  var id: Int? = null

  @Column(nullable = false)
  var name: String = ""
}
