package jp.ac.it_college.std.s22016.bookmanager.domain.repository

import jp.ac.it_college.std.s22016.bookmanager.domain.model.BookWithRental
import org.springframework.stereotype.Repository
import jp.ac.it_college.std.s22016.bookmanager.infrastructure.database.record.BookWithRental as RecordBookWithRental
import jp.ac.it_college.std.s22016.bookmanager.domain.model.Book
import jp.ac.it_college.std.s22016.bookmanager.domain.model.Rental
import jp.ac.it_college.std.s22016.bookmanager.domain.repository.BookRepository
import jp.ac.it_college.std.s22016.bookmanager.infrastructure.database.mapper.*
import jp.ac.it_college.std.s22016.bookmanager.infrastructure.database.record.Book as RecordBook

@Repository
class BookRepositoryImpl(
    private val bookWithRentalMapper: BookWithRentalMapper,
    private val bookMapper: BookMapper
) : BookRepository {

    override fun findAllWithRental(): List<BookWithRental> {
        return bookWithRentalMapper.select { }.map(::toModel)
    }

    override fun findWithRental(id: Long): BookWithRental? {
        return bookWithRentalMapper.selectByPrimaryKey(id)?.let(::toModel)
        TODO("Not yet implemented")
    }

    override fun register(book: Book) {
        bookMapper.insert(toRecord(book))
    }

    private fun toModel(record: RecordBookWithRental) : BookWithRental {
        val book = record.run {
            Book(id!!,title!!,author!!,releaseDate!!)
        }
        val rental = record.userId?.let {
            with(record) {
                Rental(id!!, userId!!, rentalDatetime!!, returnDeadline!!)
            }
        }
        return BookWithRental(book, rental)
    }
    private fun toRecord(model: Book): RecordBook
            = model.run{ RecordBook(id, title, author, releaseData) }
}

