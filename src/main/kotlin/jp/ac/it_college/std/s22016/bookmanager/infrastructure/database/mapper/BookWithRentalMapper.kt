package jp.ac.it_college.std.s22016.bookmanager.infrastructure.database.mapper

import jp.ac.it_college.std.s22016.bookmanager.infrastructure.database.mapper.BookDynamicSqlSupport.author
import jp.ac.it_college.std.s22016.bookmanager.infrastructure.database.mapper.BookDynamicSqlSupport.book
import jp.ac.it_college.std.s22016.bookmanager.infrastructure.database.mapper.BookDynamicSqlSupport.id
import jp.ac.it_college.std.s22016.bookmanager.infrastructure.database.mapper.BookDynamicSqlSupport.releaseDate
import jp.ac.it_college.std.s22016.bookmanager.infrastructure.database.mapper.BookDynamicSqlSupport.title
import jp.ac.it_college.std.s22016.bookmanager.infrastructure.database.mapper.RentalDynamicSqlSupport.rental
import jp.ac.it_college.std.s22016.bookmanager.infrastructure.database.mapper.RentalDynamicSqlSupport.rentalDatetime
import jp.ac.it_college.std.s22016.bookmanager.infrastructure.database.mapper.RentalDynamicSqlSupport.returnDeadline
import jp.ac.it_college.std.s22016.bookmanager.infrastructure.database.mapper.RentalDynamicSqlSupport.userId
import jp.ac.it_college.std.s22016.bookmanager.infrastructure.database.record.BookWithRental
import org.apache.ibatis.annotations.Mapper
import org.apache.ibatis.annotations.Result
import org.apache.ibatis.annotations.Results
import org.apache.ibatis.annotations.SelectProvider
import org.apache.ibatis.type.JdbcType
import org.mybatis.dynamic.sql.select.render.SelectStatementProvider
import org.mybatis.dynamic.sql.util.SqlProviderAdapter
import org.mybatis.dynamic.sql.util.kotlin.SelectCompleter
import java.util.concurrent.CountedCompleter
import org.mybatis.dynamic.sql.util.kotlin.mybatis3.select

@Mapper
interface BookWithRentalMapper {
    @SelectProvider(type = SqlProviderAdapter::class, method = "select")
    @Results(id = "BookWithRentalRecoedResult", value = [
        Result (column = "id", property = "id", jdbcType = JdbcType.BIGINT, id = true),
        Result (column = "id", property = "title", jdbcType = JdbcType.VARCHAR),
        Result (column = "author", property = "author", jdbcType = JdbcType.VARCHAR),
        Result (column = "release_date", property = "releaseDate", jdbcType = JdbcType.DATE),
        Result (column = "user_id", property = "userId", jdbcType = JdbcType.BIGINT),
        Result (column = "rental_datetime", property = "rentalDatetime", jdbcType = JdbcType.TIMESTAMP),
        Result (column = "return_deadline", property = "returnDeadline", jdbcType = JdbcType.TIMESTAMP)
        ]
    )
    fun selectMany(selectStatement: SelectStatementProvider): List<BookWithRental>
}

private val columnList = listOf(id, title, author, releaseDate, userId, rentalDatetime, returnDeadline)

fun BookWithRentalMapper.select(completer: SelectCompleter) =
    select(columnList) {
        from(book, "b")
        leftJoin(rental,"r") {
            on(book.id) equalTo rental.bookId
        }
        completer()
    }.run(this::selectMany)