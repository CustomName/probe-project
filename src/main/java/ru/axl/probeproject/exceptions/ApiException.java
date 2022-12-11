package ru.axl.probeproject.exceptions;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = false)
public class ApiException extends RuntimeException {

    private final ApiError apiError;

    public ApiException(final ApiError apiError, final String message) {
        super(message);
        this.apiError = apiError;
    }

}
