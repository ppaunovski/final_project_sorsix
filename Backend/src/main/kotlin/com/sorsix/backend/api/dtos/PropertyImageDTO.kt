package com.sorsix.backend.api.dtos

data class PropertyImageDTO(
    val id: Long,
    val propertyId: Long,
    val order: Int,
    val imageByteArray: ByteArray,
    val type: String,
) {
    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (other !is PropertyImageDTO) return false

        if (id != other.id) return false
        if (propertyId != other.propertyId) return false
        if (order != other.order) return false
        if (!imageByteArray.contentEquals(other.imageByteArray)) return false

        return true
    }

    override fun hashCode(): Int {
        var result = id.hashCode()
        result = 31 * result + propertyId.hashCode()
        result = 31 * result + order
        result = 31 * result + imageByteArray.contentHashCode()
        return result
    }
}
