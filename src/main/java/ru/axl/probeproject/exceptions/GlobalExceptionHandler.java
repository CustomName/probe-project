package ru.axl.probeproject.exceptions;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import javax.servlet.http.HttpServletResponse;
import java.time.OffsetDateTime;

import static ru.axl.probeproject.utils.Utils.getNowOffsetDateTime;

@Slf4j
@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(ApiException.class)
    public Error handle(HttpServletResponse res, ApiException ex){
        log.error(ex.getMessage());
        res.setStatus(ex.getApiError().getHttpCode());

        return Error.builder()
                .errorCode(ex.getApiError().getCode())
                .timestamp(getNowOffsetDateTime())
                .build();
    }

    @Data
    @Builder
    @AllArgsConstructor
    @NoArgsConstructor
    public static class Error {
        private String errorCode;
        private OffsetDateTime timestamp;
    }

}
