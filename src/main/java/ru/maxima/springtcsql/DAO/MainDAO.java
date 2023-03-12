package ru.maxima.springtcsql.DAO;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;
import ru.maxima.springtcsql.Book;
import ru.maxima.springtcsql.Person;

import javax.sql.DataSource;
import java.util.ArrayList;
import java.util.List;

@Component
public class MainDAO {

    JdbcTemplate jdbcTemplate;

    @Autowired
    public void MainDAO(DataSource dataSource) {
        jdbcTemplate = new JdbcTemplate(dataSource);
    }

    public Person getPersonById(Long id) {
        return jdbcTemplate.query("select * from persons where id = ?",
                          new Object[] {id}, new PersonMapper())
                .stream().findFirst().orElse(new Person());
    }

    public List<Person> getAllPersons() {
        return jdbcTemplate.query("select * from persons", new PersonMapper());
    }

    public boolean createPerson(Person person) {
        return jdbcTemplate.update("insert into persons(fio, birth) values(?,?)", person.getName(), person.getBirth()) > 0;
    }

    public boolean updatePerson(Person person) {
        return jdbcTemplate.update("update persons set fio = ?, birth  = ? where id = ?", person.getName(), person.getBirth(),
                person.getId()) > 0;
    }

    public boolean deletePerson(Long id) {

        return jdbcTemplate.update("update persons set is_del = true where id = ?", id) +
                jdbcTemplate.update("update books set owner = null where owner = ?", id) > 0;
    }

    public Book getBookById(Long id) {
        return jdbcTemplate.query("select * from books where id = ?", new Object[] {id}, new BookMapper())
                                                        .stream().findFirst().orElse(new Book());
    }

    public List<Book> getAllBooks() {
        return jdbcTemplate.query("select * from books", new BookMapper());
    }

    public boolean createBook(Book book) {
        return jdbcTemplate.update("insert into books(name,author,release) values(?,?,?)",
                book.getName(),book.getAuthor(),book.getRelease()) > 0;
    }

    public boolean updateBook(Book book) {
        return jdbcTemplate.update("update books set name = ?, author = ?, release = ? where id = ?",
                book.getName(),book.getAuthor(),book.getRelease(), book.getId()) > 0;
    }

    public boolean deleteBook(Long id) {
        return jdbcTemplate.update("update books set is_del = true where id = ?", id) > 0;
    }

    public List<Book> getBooksByOwner(Long ownerId) {
        return jdbcTemplate.query("select * from books where owner = ? and is_del = false",
                new Object[] {ownerId}, new BookMapper());
    }

    public boolean setBookToOwner(Long bookId, Long ownerId) {
        return jdbcTemplate.update("update books set owner = ? where id = ?",new Object[] {ownerId,bookId}) >0;
    }

    public boolean returnBook(Long id) {
        return jdbcTemplate.update("update books set owner = null where id = ?",id) > 0;
    }

}
