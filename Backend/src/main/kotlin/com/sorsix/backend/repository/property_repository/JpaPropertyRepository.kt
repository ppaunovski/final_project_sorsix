package com.sorsix.backend.repository.property_repository

import com.sorsix.backend.domain.entities.City
import com.sorsix.backend.domain.entities.Property
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.Query
import java.time.LocalDate

interface JpaPropertyRepository: JpaRepository<Property, Long>{
    @Query("select p.p_id, p_nightly_price, p_property_name, p_num_guests, p_num_beds, p_num_bedrooms, p_num_bathrooms, p_is_guest_favorite, p_description, p_address, p_longitude, p_latitude, u_id, p.c_id, pt_id "+
            "from property p " +
            "join city c on c.c_id = p.c_id " +
            "join property_availabilities pav on pav.p_id = p.p_id " +
            "where " +
            "to_tsvector(c.c_name || ' ' || p.p_property_name || ' ' || p.p_description) @@ to_tsquery(:filterString) " +
            "and pav.pav_start_date <= :checkIn and pav.pav_end_date >= :checkOut " +
            "and p.p_num_guests >= :adults + :children " +
            "and p.p_num_guests >= :pets "
        , nativeQuery = true)
    fun search(filterString: String, checkIn: LocalDate, checkOut: LocalDate, adults: Int, children: Int, pets: Int): List<Property>

    @Query("select p " +
            "from Property p " +
            "where p.isGuestFavorite = true")
    fun suggestProperties(): List<Property>
}