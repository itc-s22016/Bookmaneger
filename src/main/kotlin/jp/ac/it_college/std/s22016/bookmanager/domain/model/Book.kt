package jp.ac.it_college.std.s22016.bookmanager.domain.model

import java.time.LocalDate
data class Book (
    val id: Long,
    val title: String,
    val author: String,
    val releaseData: LocalDate,
)