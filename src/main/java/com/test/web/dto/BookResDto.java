package com.test.web.dto;

import com.test.domain.Book;
import lombok.Getter;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Getter
public class BookResDto {
    private Long id;
    private String title;
    private String author;

    public BookResDto toDto(Book entity) {
        this.id = entity.getId();
        this.title = entity.getTitle();
        this.author = entity.getAuthor();
        return this;
    }
}
