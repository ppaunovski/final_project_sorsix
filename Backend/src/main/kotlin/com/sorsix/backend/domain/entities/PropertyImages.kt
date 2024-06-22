package com.sorsix.backend.domain.entities

import jakarta.persistence.*

@Entity
@Table(name = "property_images")
data class PropertyImages(
    @Id
    @Column(name = "pi_id", nullable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long,
    @Column(name = "pi_type", nullable = false)
    val type: String,
    @ManyToOne
    @JoinColumn(name = "p_id", nullable = false)
    val property: Property,
    @Column(name = "pi_order", nullable = false)
    val order: Int,
    @Column(name = "pi_image", columnDefinition = "bytea", nullable = false)
    val image: ByteArray,
) {
    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (other !is PropertyImages) return false

        if (id != other.id) return false
        if (property != other.property) return false
        if (order != other.order) return false
        if (!image.contentEquals(other.image)) return false

        return true
    }

    override fun hashCode(): Int {
        var result = id.hashCode()
        result = 31 * result + property.hashCode()
        result = 31 * result + order
        result = 31 * result + image.contentHashCode()
        return result
    }
}
