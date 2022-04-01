package ru.axl.probeproject.exceptions;

import lombok.AllArgsConstructor;
import lombok.Getter;

import static org.springframework.http.HttpStatus.BAD_REQUEST;
import static org.springframework.http.HttpStatus.NOT_FOUND;

@Getter
@AllArgsConstructor
public enum ApiError {

    CLIENT_NOT_FOUND("CLIENT_NOT_FOUND", NOT_FOUND.value()),
    PROCESS_NOT_FOUND("PROCESS_NOT_FOUND", NOT_FOUND.value()),
    PROCESS_STATUS_NOT_FOUND("PROCESS_STATUS_NOT_FOUND", NOT_FOUND.value()),
    ACCOUNT_STATUS_NOT_FOUND("ACCOUNT_STATUS_NOT_FOUND", NOT_FOUND.value()),
    CURRENCY_NOT_FOUND("CURRENCY_NOT_FOUND", NOT_FOUND.value()),
    CLIENT_HAS_NOT_TERMINATED_PROCESSES("CLIENT_HAS_NOT_TERMINATED_PROCESSES", BAD_REQUEST.value());

    private final String code;
    private final int httpCode;

}
