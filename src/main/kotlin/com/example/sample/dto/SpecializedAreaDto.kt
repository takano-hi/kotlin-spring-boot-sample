package com.example.sample.dto

import com.fasterxml.jackson.annotation.JsonCreator

data class SpecializedAreaDto @JsonCreator constructor (
  val id: Int,
  val name: String
)
