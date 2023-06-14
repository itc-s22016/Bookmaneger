package jp.ac.it_college.std.s22016.bookmanager.presentation.contoroller

import jp.ac.it_college.std.s22016.bookmanager.application.service.AdminBookService
import jp.ac.it_college.std.s22016.bookmanager.domain.model.Book
import jp.ac.it_college.std.s22016.bookmanager.presentation.form.RentalInfo
import org.springframework.web.bind.annotation.CrossOrigin
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("admin/book")
@CrossOrigin

class AdminBookController (
    private val adminBookService: AdminBookService
){
    @PostMapping("/register")
    fun register(@RequestBody request: RentalInfo.RegisterBookRequest) {
        adminBookService.register(
        Book(
            request.id,
            request.title,
            request.author,
            request.releaseDate
            )
        )
    }
}