package ru.maxima.springtcsql.DAO;

import org.springframework.jdbc.core.RowMapper;
import ru.maxima.springtcsql.Book;
import ru.maxima.springtcsql.Person;

import java.sql.ResultSet;
import java.sql.SQLException;

public class BookMapper implements RowMapper<Book> {
    @Override
    public Book mapRow(ResultSet resultSet, int rowNum) throws SQLException {
        Book book = new Book(resultSet.getLong("id"),
                             resultSet.getString("name"),
                             resultSet.getString("author"),
                             resultSet.getInt("releaseyear"),
                             resultSet.getLong("owner"));
        return book;
    }
}
