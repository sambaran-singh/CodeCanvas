package com.sambaran.codecanvas.error;

import com.fasterxml.jackson.annotation.JsonInclude;
import org.apache.tomcat.util.json.JSONFilter;
import org.springframework.http.HttpStatus;

import java.time.Instant;
import java.util.List;

/**
 * @author Sambaran
 */
public record ApiError(
        String message,
        HttpStatus status,
        Instant timeStamp,
        @JsonInclude(JsonInclude.Include.NON_NULL)List<ApiFieldError> errors
) {
    public ApiError(String message, HttpStatus status){
        this(message, status, Instant.now(), null);
    }

    public ApiError(String message, HttpStatus status, List<ApiFieldError> errors){
        this(message, status, Instant.now(), errors);
    }
}


record ApiFieldError(
        String field,
        String message
){

}