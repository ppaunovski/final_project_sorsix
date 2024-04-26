package com.sorsix.backend.domain.entities

import jakarta.persistence.*

@Table(name = "property_attribute")
@Entity
data class PropertyAttribute(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "pa_id")
    val paId: Long,
    @ManyToOne
    @JoinColumn(name = "p_id")
    val property: Property,
    @ManyToOne
    @JoinColumn(name = "a_id")
    val attribute: Attribute
)
