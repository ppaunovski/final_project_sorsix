package com.sorsix.backend.repository.user_account_repository

import com.sorsix.backend.api.dtos.ProfitsPerPropertyDTO
import com.sorsix.backend.domain.entities.UserAccount
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.Query

interface JpaUserAccountRepository : JpaRepository<UserAccount, Long> {
    fun findByEmail(email: String): UserAccount?

    @Query(
        "select new com.sorsix.backend.api.dtos.ProfitsPerPropertyDTO(p.id, p.name, cast(sum(b.nightlyPrice * (b.checkOut - b.checkIn)) as double), sum(bg.numGuests)) " +
                "from UserAccount u " +
                "join Property p on u.id = p.host.id " +
                "join Booking b on p.id = b.property.id " +
                "join BookingStatus bs on b.status.id = bs.id " +
                "join BookingGuest bg on b.id = bg.booking.id " +
                "join GuestType gt on bg.guestType.id = gt.id " +
                "where " +
                "bs.name = 'APPROVED' and " +
                "u = :userAccount " +
                "group by p.id, p.name",
    )
    fun getProfitsPerProperty(userAccount: UserAccount): List<ProfitsPerPropertyDTO>

}