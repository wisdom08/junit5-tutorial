package com.test.domain;

import com.test.Junit5TutorialApplication;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.jdbc.Sql;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;

@ContextConfiguration(classes = Junit5TutorialApplication.class)
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@DataJpaTest // db와 관련된 컴포넌트만 메모리에 로딩(컨트롤러와 서비스 X)
class BookRepositoryTest {

    @Autowired
    private BookRepository bookRepository;

    @BeforeEach
    public void prepareData() {
        String title = "junit5";
        String author = "wisdom";
        Book book = Book.builder()
                .title(title)
                .author(author)
                .build();

        bookRepository.save(book);
    }

    @Test
    void 책등록_테스트() {
        //given & when
        String title = "junit5";
        String author = "wisdom";
        Book book = Book.builder()
                .title(title)
                .author(author)
                .build();

        Book savedBook = bookRepository.save(book);

        //then
        assertEquals(title, savedBook.getTitle());
        assertEquals(author, savedBook.getAuthor());
    }

    @Test
    void 책목록보기_테스트() {
        //given & when
        List<Book> books = bookRepository.findAll();

        //then
        assertEquals("junit5", books.get(0).getTitle());
        assertEquals("wisdom", books.get(0).getAuthor());
        assertEquals(1, books.size());
    }

    @Sql("classpath:db/tableInit.sql")
    @Test
    void 책한건보기_테스트() {
        //given & when
        Book savedBook = bookRepository.findById(1L).get();

        //then
        assertEquals("junit5", savedBook.getTitle());
        assertEquals("wisdom", savedBook.getAuthor());
    }

    @Test
    void 책수정_테스트() {
        //given & when
        Long id = 1L;
        String title = "test";
        String author = "jihye";
        Book book = new Book(id, title, author);

        Book modifiedBook = bookRepository.save(book);

        //then
        assertEquals(title, modifiedBook.getTitle());
        assertEquals(author, modifiedBook.getAuthor());
    }

    @Test
    void 책삭제_테스트() {
        //given & when
        Long id = 1L;
        bookRepository.deleteById(id);

        //then
        assertFalse(bookRepository.findById(id).isPresent());
    }
}