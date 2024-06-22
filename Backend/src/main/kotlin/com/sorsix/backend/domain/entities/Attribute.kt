package com.sorsix.backend.domain.entities

import jakarta.persistence.Column
import jakarta.persistence.Entity
import jakarta.persistence.GeneratedValue
import jakarta.persistence.GenerationType
import jakarta.persistence.Id
import jakarta.persistence.Table

@Entity
@Table(name = "attribute")
data class Attribute(
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "a_id", nullable = false)
    val id: Long,
    @Column(name = "a_name", nullable = false)
    var name: String,
    @Column(name = "a_description", nullable = false)
    var description: String,
    @Column(name = "a_icon")
    val icon: String,
)
