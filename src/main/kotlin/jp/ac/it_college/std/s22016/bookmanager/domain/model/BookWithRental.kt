package jp.ac.it_college.std.s22016.bookmanager.domain.model

data class BookWithRental (
    val book: Book,
    val rental: Rental?
){
    val isRental: Boolean
        get() = rental != null
}