package com.sorsix.backend.domain.entities

import jakarta.persistence.*

@Table(name = "city")
@Entity
data class City(
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "c_id")
    val id: Long,
    @Column(name = "c_name", nullable = false)
    var name: String,
)
