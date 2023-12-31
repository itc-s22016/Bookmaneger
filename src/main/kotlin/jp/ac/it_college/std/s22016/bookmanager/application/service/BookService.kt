package jp.ac.it_college.std.s22016.bookmanager.application.service

import jp.ac.it_college.std.s22016.bookmanager.domain.model.BookWithRental
import jp.ac.it_college.std.s22016.bookmanager.domain.repository.BookRepository
import org.springframework.stereotype.Service
import java.lang.IllegalArgumentException

@Service
class BookService (
    private val bookRepository: BookRepository
) {
    fun getList(): List<BookWithRental> {
        return bookRepository.findAllWithRental()
}

fun getDetail(bookId: Long): BookWithRental {
    return bookRepository.findWithRental(bookId)
        ?: throw IllegalArgumentException("存在しない書籍IDです。")
    }
}