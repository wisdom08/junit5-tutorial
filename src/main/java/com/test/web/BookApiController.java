package com.test.web;

import com.test.service.BookService;
import com.test.web.dto.request.BookSaveReqDto;
import com.test.web.dto.response.BookResDto;
import com.test.web.dto.response.CommonResponseDto;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.util.HashMap;
import java.util.Map;

@RequiredArgsConstructor
@RequestMapping("/api/v1/book")
@RestController
public class BookApiController {

    private final BookService bookService;

    @PostMapping
    public ResponseEntity<?> saveBook(@RequestBody @Valid BookSaveReqDto bookSaveReqDto, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            Map<String, String> errorMap = new HashMap<>();
            for (FieldError fieldError : bindingResult.getFieldErrors()) {
                errorMap.put(fieldError.getField(), fieldError.getDefaultMessage());
            }
            throw new RuntimeException(errorMap.toString());
        }

        BookResDto dto = bookService.createBook(bookSaveReqDto);

        return new ResponseEntity<>(CommonResponseDto.builder()
                .code(1)
                .message("글 저장 성공")
                .body(dto).build(), HttpStatus.CREATED);
    }
}
