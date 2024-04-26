package com.sorsix.backend.domain.entities

import jakarta.persistence.*
import java.time.LocalDate

@Table(name = "user_account")
@Entity
data class UserAccount(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "u_id", nullable = false)
    val id: Long,
    @Column(name = "u_email", nullable = false)
    var email: String,
    @Column(name = "u_first_name", nullable = false)
    var firstName: String,
    @Column(name = "u_last_name", nullable = false)
    var lastName: String,
    @Column(name = "u_password", nullable = false)
    var password: String,
    @Column(name = "u_joined_date", nullable = false)
    var joinedDate: LocalDate,
    @Column(name = "u_date_host_started")
    var dateHostStarted: LocalDate?,
)