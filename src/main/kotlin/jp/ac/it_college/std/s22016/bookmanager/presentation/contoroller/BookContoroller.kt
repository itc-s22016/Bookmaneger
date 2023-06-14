package jp.ac.it_college.std.s22016.bookmanager.presentation.contoroller

import jp.ac.it_college.std.s22016.bookmanager.application.service.BookService
import jp.ac.it_college.std.s22016.bookmanager.presentation.form.BookInfo
import jp.ac.it_college.std.s22016.bookmanager.presentation.form.GetBookDetailResponse
import jp.ac.it_college.std.s22016.bookmanager.presentation.form.GetBookListResponse
import org.springframework.web.bind.annotation.CrossOrigin
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("book")
@CrossOrigin
class BookContoroller (
    private val bookService: BookService
){
    @GetMapping("/list")
    fun getList(): GetBookListResponse {
        val bookList = bookService.getList().map {
            BookInfo(it)
        }
        return GetBookListResponse(bookList)
    }
    @GetMapping("/detail/{book_id}")
    fun getDetail(@PathVariable("book_id") bookId: Long): GetBookDetailResponse {
        val book = bookService.getDetail(bookId)
        return GetBookDetailResponse(book)
    }
}