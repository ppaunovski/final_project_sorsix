package com.sorsix.backend.domain.entities

import jakarta.persistence.Column
import jakarta.persistence.Entity
import jakarta.persistence.GeneratedValue
import jakarta.persistence.GenerationType
import jakarta.persistence.Id
import jakarta.persistence.JoinColumn
import jakarta.persistence.OneToOne

@Entity
data class UserImage(
    @Id
    @Column(name = "ui_id", nullable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long,
    @Column(name = "ui_image", nullable = false)
    val image: ByteArray,
    @Column(name = "ui_type", nullable = false)
    val type: String,
    @OneToOne
    @JoinColumn(name = "u_id")
    val user: UserAccount,
)
