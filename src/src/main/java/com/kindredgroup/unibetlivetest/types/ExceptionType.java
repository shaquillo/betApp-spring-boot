package com.kindredgroup.unibetlivetest.types;

import lombok.Getter;
import org.springframework.http.HttpStatus;

public enum ExceptionType {

    EXCEPTION_NO_CONTENT(HttpStatus.NO_CONTENT),
    
    EXCEPTION_NOT_FOUND(HttpStatus.NOT_FOUND),

    EXCEPTION_CONFLICT(HttpStatus.CONFLICT),

    EXCEPTION_BAD_REQUEST(HttpStatus.BAD_REQUEST),
    
    EXCEPTION_NOT_ACCEPTABLES(HttpStatus.NOT_ACCEPTABLE);
        
    @Getter
    final HttpStatus status;

    ExceptionType(HttpStatus status) {
        this.status = status;
    }

}
