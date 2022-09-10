package com.test.web.dto.response;

import lombok.Builder;
import lombok.Getter;

@Getter
public class CommonResponseDto<T> {
    private final Integer code; // 1성공 -1실패
    private final String message; // 에러 메시지, 성공 메시지
    private final T body;

    @Builder
    public CommonResponseDto(Integer code, String message, T body) {
        this.code = code;
        this.message = message;
        this.body = body;
    }
}
