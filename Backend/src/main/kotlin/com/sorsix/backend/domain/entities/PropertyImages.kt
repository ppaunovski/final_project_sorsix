package com.sorsix.backend.domain.entities

import jakarta.persistence.*

@Entity
@Table(name = "property_images")
data class PropertyImages(
    @Id
    @Column(name = "pi_id", nullable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long,
    @ManyToOne
    @JoinColumn(name = "p_id", nullable = false)
    val property: Property,
    @Column(name = "pi_order", nullable = false)
    val order: Int
)
