package com.sorsix.backend.domain.entities

import jakarta.persistence.*

@Entity
@Table(name = "favorite")
data class Favorite(
    @Id
    @Column(name = "f_id", nullable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long,
    @ManyToOne
    @JoinColumn(name = "u_id", nullable = false)
    val user: UserAccount,
    @ManyToOne
    @JoinColumn(name = "p_id", nullable = false)
    val property: Property
)
