package com.sorsix.backend.domain.entities

import jakarta.persistence.*

@Entity
@Table(name = "property")
data class Property(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "p_id", nullable = false)
    val id: Long,
    @Column(name = "p_nightly_price", nullable = false)
    var nightlyPrice: Double,
    @Column(nullable = false, name = "p_property_name")
    var name: String,
    @Column(nullable = false, name = "p_num_guests")
    var guests: Int,
    @Column(nullable = false, name = "p_num_beds")
    var beds: Int,
    @Column(nullable = false, name = "p_num_bedrooms")
    var bedrooms: Int,
    @Column(nullable = false, name = "p_num_bathrooms")
    var bathrooms: Int,
    @Column(nullable = false, name = "p_is_guest_favorite")
    var isGuestFavorite: Boolean,
    @Column(nullable = false, name = "p_description")
    var description: String,
    @Column(nullable = false, name = "p_address")
    var address: String,
    @Column(nullable = false, name = "p_longitude")
    var longitude: Double,
    @Column(nullable = false, name = "p_latitude")
    var latitude: Double,
    @ManyToOne
    @JoinColumn(name = "u_id")
    var host: UserAccount,
    @ManyToOne
    @JoinColumn(name = "c_id")
    var city: City,
    @ManyToOne
    @JoinColumn(name = "pt_id")
    var propertyType: PropertyType,
)
