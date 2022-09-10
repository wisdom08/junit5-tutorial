package com.test.service;

import com.test.domain.BookRepository;
import com.test.web.dto.BookResDto;
import com.test.web.dto.BookSaveReqDto;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import static org.junit.jupiter.api.Assertions.*;

@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@DataJpaTest
class BookServiceTest {

    @Autowired
    private BookRepository bookRepository;

    @Test
    void createBook() {
        BookSaveReqDto dto = new BookSaveReqDto();
        dto.setTitle("스프링부트");
        dto.setAuthor("변지혜");

        BookService bookService = new BookService(bookRepository);
        BookResDto bookResDto  = bookService.createBook(dto);

        assertEquals(dto.getTitle(), bookResDto.getTitle());
        assertEquals(dto.getAuthor(), bookResDto.getAuthor());
    }

    @Test
    void getBookList() {
    }

    @Test
    void getBook() {
    }

    @Test
    void deleteBook() {
    }

    @Test
    void updateBook() {
    }
}