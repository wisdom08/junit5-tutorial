package com.test.service;

import com.test.domain.Book;
import com.test.domain.BookRepository;
import com.test.web.dto.BookResDto;
import com.test.web.dto.BookSaveReqDto;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class BookServiceTest {

    @InjectMocks
    private BookService bookService;

    @Mock
    private BookRepository bookRepository;

    @Test
    void createBook() {
        BookSaveReqDto dto = new BookSaveReqDto();
        dto.setTitle("스프링부트");
        dto.setAuthor("변지혜");

        // stub(가설)
        when(bookRepository.save(any())).thenReturn(dto.toEntity());

        BookResDto bookResDto  = bookService.createBook(dto);

        assertThat(bookResDto.getTitle()).isEqualTo(dto.getTitle());
        assertThat(bookResDto.getAuthor()).isEqualTo(dto.getAuthor());
    }

    @Test
    void getBookList() {
        List<Book> books = new ArrayList<>();
        books.add(new Book(1L, "스프링", "지혜"));
        books.add(new Book(2L, "자바", "J"));
        when(bookRepository.findAll()).thenReturn(books);

        List<BookResDto> bookResDtoList = bookService.getBookList();


        assertThat(bookResDtoList.get(0).getTitle()).isEqualTo("스프링");
        assertThat(bookResDtoList.get(0).getAuthor()).isEqualTo("지혜");

        assertThat(bookResDtoList.get(1).getTitle()).isEqualTo("자바");
        assertThat(bookResDtoList.get(1).getAuthor()).isEqualTo("J");
    }

    @Test
    void getBook() {
        Long id = 1L;

        Book book = new Book(1L, "junit", "wisdom");
        Optional<Book> optionalBook = Optional.of(book);
        when(bookRepository.findById(id)).thenReturn(optionalBook);

        BookResDto dto = bookService.getBook(id);

        assertThat(dto.getId()).isEqualTo(book.getId());
        assertThat(dto.getTitle()).isEqualTo(book.getTitle());
        assertThat(dto.getAuthor()).isEqualTo(book.getAuthor());
    }

    @Test
    void updateBook() {
        Long id = 1L;
        BookSaveReqDto dto = new BookSaveReqDto();
        dto.setTitle("java");
        dto.setAuthor("jj");

        Book book = new Book(1L, "junit", "wisdom");
        Optional<Book> optionalBook = Optional.of(book);
        when(bookRepository.findById(id)).thenReturn(optionalBook);

        BookResDto bookResDto = bookService.updateBook(id, dto);

        assertThat(bookResDto.getTitle()).isEqualTo(dto.getTitle());
        assertThat(bookResDto.getAuthor()).isEqualTo(dto.getAuthor());
    }
}