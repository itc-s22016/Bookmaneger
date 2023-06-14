package jp.ac.it_college.std.s22016.bookmanager.application.service

import jp.ac.it_college.std.s22016.bookmanager.domain.model.Book
import jp.ac.it_college.std.s22016.bookmanager.domain.repository.BookRepository
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
class AdminBookService (
    private val bookRepository: BookRepository
    ) {

    @Transactional
    fun register(book: Book) {
        bookRepository.findWithRental(book.id)
            ?.let { throw java.lang.IllegalArgumentException("既に存在する書籍ID: \${book.id}") }
        bookRepository.register(book)
    }
}
