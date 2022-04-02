package ru.axl.probeproject.exceptions;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import javax.servlet.http.HttpServletResponse;
import javax.validation.ValidationException;
import java.time.OffsetDateTime;

import static org.springframework.http.HttpStatus.INTERNAL_SERVER_ERROR;
import static ru.axl.probeproject.utils.Utils.getNowOffsetDateTime;

@Slf4j
@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(ApiException.class)
    public Error handleApiException(HttpServletResponse res, ApiException ex){
        log.error(ex.getMessage());
        res.setStatus(ex.getApiError().getHttpCode());

        return Error.builder()
                .errorCode(ex.getApiError().getCode())
                .message("")
                .timestamp(getNowOffsetDateTime())
                .build();
    }

    @ExceptionHandler(Exception.class)
    public Error handleException(HttpServletResponse res, Exception ex){
        log.error(ex.getMessage());
        res.setStatus(INTERNAL_SERVER_ERROR.value());

        return Error.builder()
                .errorCode("INTERNAL_SERVER_ERROR")
                .message(ex.getMessage())
                .timestamp(getNowOffsetDateTime())
                .build();
    }

    @ExceptionHandler(ValidationException.class)
    public Error handleException(HttpServletResponse res, ValidationException ex){
        log.error(ex.getMessage());
        res.setStatus(INTERNAL_SERVER_ERROR.value());

        return Error.builder()
                .errorCode("VALIDATION_ERROR")
                .message(ex.getMessage())
                .timestamp(getNowOffsetDateTime())
                .build();
    }

    @Data
    @Builder
    @AllArgsConstructor
    @NoArgsConstructor
    public static class Error {
        private String errorCode;
        private String message;
        private OffsetDateTime timestamp;
    }

}
