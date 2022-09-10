package com.test.service;

import com.test.domain.Book;
import com.test.domain.BookRepository;
import com.test.web.dto.BookResDto;
import com.test.web.dto.BookSaveReqDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Service
public class BookService {

    private final BookRepository bookRepository;

    @Transactional(rollbackFor = RuntimeException.class)
    public BookResDto createBook(BookSaveReqDto dto) {
        Book savedBook = bookRepository.save(dto.toEntity());
        return savedBook.toDto();
    }

    public List<BookResDto> getBookList() {
        return bookRepository.findAll().stream()
                .map(Book::toDto)
                .collect(Collectors.toList());
    }

    public BookResDto getBook(Long id) {
        Optional<Book> book = bookRepository.findById(id);
        if (book.isPresent()) {
            Book foundBook = book.get();
            return foundBook.toDto();
        } else {
            throw new RuntimeException("해당 아이디를 찾을 수 없습니다.");
        }
    }

    @Transactional(rollbackFor = RuntimeException.class)
    public void deleteBook(Long id) {
        bookRepository.deleteById(id);
    }

    public BookResDto updateBook(Long id, BookSaveReqDto dto) {
        Optional<Book> optionalBook = bookRepository.findById(id);
        if (optionalBook.isPresent()) {
            Book book = optionalBook.get();
            book.update(dto.getTitle(), dto.getAuthor());
            return book.toDto();
        } else {
            throw new RuntimeException("해당 아이디를 찾을 수 없습니다.");
        }
    }
}
