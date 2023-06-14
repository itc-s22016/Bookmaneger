package jp.ac.it_college.std.s22016.bookmanager.domain.repository

import jp.ac.it_college.std.s22016.bookmanager.domain.model.Book
import jp.ac.it_college.std.s22016.bookmanager.domain.model.BookWithRental

interface  BookRepository {
    fun findAllWithRental(): List<BookWithRental>
    fun findWithRental(id: Long): BookWithRental?
    fun register(book: Book)
}
