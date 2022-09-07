package com.test.domain;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@DataJpaTest // db와 관련된 컴포넌트만 메모리에 로딩(컨트롤러와 서비스 X)
class BookRepositoryTest {

    @Autowired
    private BookRepository bookRepository;

    @Test
    void 책등록_테스트() {
        //given & when

        //then

    }
    // 2. 책 목록 보기
    // 3. 책 한 건 보기
    // 4. 책 수정
    // 5. 책 삭제
}