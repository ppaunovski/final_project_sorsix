package com.sorsix.backend.domain.entities

import jakarta.persistence.Column
import jakarta.persistence.Entity
import jakarta.persistence.GeneratedValue
import jakarta.persistence.GenerationType
import jakarta.persistence.Id
import jakarta.persistence.JoinColumn
import jakarta.persistence.ManyToOne
import jakarta.persistence.Table

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
    val attribute: Attribute,
)
