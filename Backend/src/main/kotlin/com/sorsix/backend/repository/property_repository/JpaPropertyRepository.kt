package com.sorsix.backend.repository.property_repository

import com.sorsix.backend.domain.entities.City
import com.sorsix.backend.domain.entities.Property
import com.sorsix.backend.domain.entities.UserAccount
import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.Query
import java.time.LocalDate

interface JpaPropertyRepository: JpaRepository<Property, Long>{
    @Query("select distinct p.p_id, p_nightly_price, p_property_name, p_num_guests, p_num_beds, p_num_bedrooms, p_num_bathrooms, p_is_guest_favorite, p_description, p_address, p_longitude, p_latitude, u_id, p.c_id, pt_id "+
            "from property p " +
            "join city c on c.c_id = p.c_id " +
            "join property_availabilities pav on pav.p_id = p.p_id " +
            "where " +
            "to_tsvector(c.c_name || ' ' || p.p_property_name || ' ' || p.p_description) @@ plainto_tsquery(:filterString) " +
            "and pav.pav_start_date <= :checkIn and pav.pav_end_date >= :checkOut " +
            "and p.p_num_guests >= :adults " +
            "and p.p_num_guests >= :children " +
            "and p.p_num_guests >= :pets "
        , nativeQuery = true)
    fun search(filterString: String, checkIn: LocalDate, checkOut: LocalDate, adults: Int, children: Int, pets: Int): List<Property>

    @Query("select p " +
            "from Property p " +
            "where p.isGuestFavorite = true")
    fun suggestProperties(): List<Property>

    fun findAllByHost(host: UserAccount): List<Property>

    @Query("select distinct p.p_id, p_nightly_price, p_property_name, p_num_guests, p_num_beds, p_num_bedrooms, p_num_bathrooms, p_is_guest_favorite, p_description, p_address, p_longitude, p_latitude, u_id, p.c_id, pt_id "+
            "from property p " +
            "join city c on c.c_id = p.c_id " +
            "where " +
            "to_tsvector(c.c_name || ' ' || p.p_property_name || ' ' || p.p_description) @@ plainto_tsquery(:filterString) " +
            "and p.p_num_guests >= :adults " +
            "and p.p_num_guests >= :children " +
            "and p.p_num_guests >= :pets "
        , nativeQuery = true)
    fun findWithoutDates(filterString: String, adults: Int, children: Int, pets: Int): List<Property>

    @Query("select distinct p.p_id, p_nightly_price, p_property_name, p_num_guests, p_num_beds, p_num_bedrooms, p_num_bathrooms, p_is_guest_favorite, p_description, p_address, p_longitude, p_latitude, u_id, p.c_id, pt_id "+
            "from property p " +
            "join city c on c.c_id = p.c_id " +
            "join property_availabilities pav on pav.p_id = p.p_id " +
            "where " +
            "to_tsvector(c.c_name || ' ' || p.p_property_name || ' ' || p.p_description) @@ plainto_tsquery(:filterString) " +
            "and pav.pav_start_date <= :checkIn "+
            "and p.p_num_guests >= :adults " +
            "and p.p_num_guests >= :children " +
            "and p.p_num_guests >= :pets "
        , nativeQuery = true)
    fun findWithCheckIn(filterString: String, checkIn: LocalDate, adults: Int, children: Int, pets: Int): List<Property>

    @Query("select p.p_id, p_nightly_price, p_property_name, p_num_guests, p_num_beds, p_num_bedrooms, p_num_bathrooms, p_is_guest_favorite, p_description, p_address, p_longitude, p_latitude, p.u_id, p.c_id, pt_id "+
            "from property p " +
            "join city c on c.c_id = p.c_id " +
            "join property_availabilities pav on pav.p_id = p.p_id " +
            "left join user_review ur on ur.p_id = p.p_id " +
            "left join component_rating cr on cr.ur_id = ur.ur_id " +
            "where " +
            "to_tsvector(c.c_name || ' ' || p.p_property_name || ' ' || p.p_description) @@ plainto_tsquery(:filterString) " +
            "and pav.pav_start_date <= :checkIn and pav.pav_end_date >= :checkOut " +
            "and p.p_num_guests >= :adults " +
            "and p.p_num_guests >= :children " +
            "and p.p_num_guests >= :pets " +
            "group by p.p_id, p_nightly_price, p_property_name, p_num_guests, p_num_beds, p_num_bedrooms, p_num_bathrooms, p_is_guest_favorite, p_description, p_address, p_longitude, p_latitude, p.u_id, p.c_id, pt_id " +
            "order by avg(cr.cr_rating) desc",
        countQuery = "select count(*) " +
                "from property p " +
                "join city c on c.c_id = p.c_id " +
                "join property_availabilities pav on pav.p_id = p.p_id " +
                "where " +
                "to_tsvector(c.c_name || ' ' || p.p_property_name || ' ' || p.p_description) @@ plainto_tsquery(:filterString) " +
                "and pav.pav_start_date <= :checkIn and pav.pav_end_date >= :checkOut " +
                "and p.p_num_guests >= :adults " +
                "and p.p_num_guests >= :children " +
                "and p.p_num_guests >= :pets "
        , nativeQuery = true)
    fun filterWithPagination(filterString: String, checkIn: LocalDate, checkOut: LocalDate, adults: Int, children: Int, pets: Int, pageable: Pageable): Page<Property>

    @Query("select  p.p_id, p_nightly_price, p_property_name, p_num_guests, p_num_beds, p_num_bedrooms, p_num_bathrooms, p_is_guest_favorite, p_description, p_address, p_longitude, p_latitude, p.u_id, p.c_id, pt_id "+
            "from property p " +
            "join city c on c.c_id = p.c_id " +
            "left join user_review ur on ur.p_id = p.p_id " +
            "left join component_rating cr on cr.ur_id = ur.ur_id " +
            "where " +
            "to_tsvector(c.c_name || ' ' || p.p_property_name || ' ' || p.p_description) @@ plainto_tsquery(:filterString) " +
            "and p.p_num_guests >= :adults " +
            "and p.p_num_guests >= :children " +
            "and p.p_num_guests >= :pets " +
            "group by p.p_id, p_nightly_price, p_property_name, p_num_guests, p_num_beds, p_num_bedrooms, p_num_bathrooms, p_is_guest_favorite, p_description, p_address, p_longitude, p_latitude, p.u_id, p.c_id, pt_id " +
            "order by avg(cr.cr_rating) desc",
        countQuery = "select  count(p.p_id) " +
                "from property p " +
                "join city c on c.c_id = p.c_id " +
                "where " +
                "to_tsvector(c.c_name || ' ' || p.p_property_name || ' ' || p.p_description) @@ plainto_tsquery(:filterString) " +
                "and p.p_num_guests >= :adults " +
                "and p.p_num_guests >= :children " +
                "and p.p_num_guests >= :pets "
        , nativeQuery = true)
    fun findAllByFilterString(filterString: String, adults: Int, children: Int, pets: Int, pageable: Pageable): Page<Property>

    @Query("select  p.p_id, p_nightly_price, p_property_name, p_num_guests, p_num_beds, p_num_bedrooms, p_num_bathrooms, p_is_guest_favorite, p_description, p_address, p_longitude, p_latitude, p.u_id, p.c_id, pt_id "+
            "from property p " +
            "join city c on c.c_id = p.c_id " +
            "left join user_review ur on ur.p_id = p.p_id " +
            "left join component_rating cr on cr.ur_id = ur.ur_id " +
            "group by p.p_id, p_nightly_price, p_property_name, p_num_guests, p_num_beds, p_num_bedrooms, p_num_bathrooms, p_is_guest_favorite, p_description, p_address, p_longitude, p_latitude, p.u_id, p.c_id, pt_id " +
            "order by avg(cr.cr_rating) desc",
        countQuery = "select  count(p.p_id) " +
                "from property p " +
                "join city c on c.c_id = p.c_id "
        , nativeQuery = true)
    fun findAllPaginated(pageable: Pageable): Page<Property>


}