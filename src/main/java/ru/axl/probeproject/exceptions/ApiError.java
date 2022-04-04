package ru.axl.probeproject.exceptions;

import lombok.AllArgsConstructor;
import lombok.Getter;

import static org.springframework.http.HttpStatus.BAD_REQUEST;
import static org.springframework.http.HttpStatus.NOT_FOUND;

@Getter
@AllArgsConstructor
public enum ApiError {

    CLIENT_NOT_FOUND("CLIENT_NOT_FOUND", "Клиент не найден", NOT_FOUND.value()),
    PROCESS_NOT_FOUND("PROCESS_NOT_FOUND",
            "Процесс не найден, либо его статус некорректен", NOT_FOUND.value()),
    PROCESS_TOO_MUCH("PROCESS_TOO_MUCH",
            "Найдено более одного процесса в активном статусе", NOT_FOUND.value()),
    PROCESS_STATUS_NOT_FOUND("PROCESS_STATUS_NOT_FOUND", "Статус процесса не найден", NOT_FOUND.value()),
    ACCOUNT_STATUS_NOT_FOUND("ACCOUNT_STATUS_NOT_FOUND", "Статус счета не найден", NOT_FOUND.value()),
    RESERVED_ACCOUNTS_NOT_FOUND("RESERVED_ACCOUNTS_NOT_FOUND",
            "Нет зарезервированных счетов", NOT_FOUND.value()),
    CURRENCY_NOT_FOUND("CURRENCY_NOT_FOUND", "Код валюты не найден", NOT_FOUND.value()),
    CLIENT_HAS_NOT_TERMINATED_PROCESSES("CLIENT_HAS_NOT_TERMINATED_PROCESSES",
            "У клиента есть незавершенные процессы", BAD_REQUEST.value());

    private final String code;
    private final String message;
    private final int httpCode;

}
