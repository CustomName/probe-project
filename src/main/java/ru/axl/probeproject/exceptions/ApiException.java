package ru.axl.probeproject.exceptions;

import lombok.Data;

@Data
public class ApiException extends RuntimeException{

    private final ApiError apiError;

    public ApiException(ApiError apiError, String message){
        super(message);
        this.apiError = apiError;
    }

}
