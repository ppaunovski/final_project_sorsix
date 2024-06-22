package com.sorsix.backend.domain.entities

import jakarta.persistence.*
import java.time.LocalDate

@Entity
@Table(name = "user_review")
data class UserReview(
    @Id
    @Column(name = "ur_id", nullable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long,
    @ManyToOne
    @JoinColumn(name = "u_id", nullable = false)
    val user: UserAccount,
    @ManyToOne
    @JoinColumn(name = "p_id", nullable = false)
    val property: Property,
    @Column(name = "ur_comment", nullable = false)
    val comment: String,
    @Column(name = "ur_review_date", nullable = false)
    val reviewDate: LocalDate,
    @OneToOne
    @JoinColumn(name = "b_id", nullable = false)
    val booking: Booking,
)
