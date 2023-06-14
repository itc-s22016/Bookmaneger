package jp.ac.it_college.std.s22016.bookmanager.domain.model

import java.time.LocalDateTime

data class Rental (
    val bookId: Long,
    val userId: Long,
    val rentalDatetime: LocalDateTime,
    val returnDeadline: LocalDateTime
)