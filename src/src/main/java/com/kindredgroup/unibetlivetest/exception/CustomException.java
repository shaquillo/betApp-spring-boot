package com.kindredgroup.unibetlivetest.exception;

import com.kindredgroup.unibetlivetest.types.ExceptionType;
import lombok.Data;

@Data
public class CustomException extends RuntimeException {

    private final ExceptionType exception;
    private final String message;

    public CustomException(String message, ExceptionType exception) {
        this.message = message;
        this.exception = exception;
    }

}
