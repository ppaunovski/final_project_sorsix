package com.sorsix.backend.domain.entities

import jakarta.persistence.Column
import jakarta.persistence.Entity
import jakarta.persistence.GeneratedValue
import jakarta.persistence.GenerationType
import jakarta.persistence.Id
import jakarta.persistence.Table

@Table(name = "property_type")
@Entity
data class PropertyType(
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "pt_id", nullable = false)
    val id: Long,
    @Column(name = "pt_type_name", nullable = false)
    val typeName: String,
)
