package com.sorsix.backend.domain.entities

import jakarta.persistence.*
import java.time.LocalDate

@Table(name = "property_availabilities")
@Entity
data class PropertyAvailability(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "pav_id")
    val id: Long,

    @Column(name = "pav_start_date")
    val startDate: LocalDate,

    @Column(name = "pav_end_date")
    val endDate: LocalDate,

    @JoinColumn(name = "p_id")
    @ManyToOne
    val property: Property

    )
