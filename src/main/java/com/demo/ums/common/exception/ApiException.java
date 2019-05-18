package com.demo.ums.common.exception;

import org.springframework.http.HttpStatus;

/**
 * Created on 2017/5/8.
 *
 * @author vikde
 */
public class ApiException extends Exception {
    private HttpStatus httpStatus;

    public ApiException(HttpStatus httpStatus) {
        super(httpStatus.getReasonPhrase(), null, false, false);
        this.httpStatus = httpStatus;
    }

    public ApiException(String message) {
        super(message, null, false, false);
        this.httpStatus = HttpStatus.BAD_REQUEST;
    }

    public ApiException(HttpStatus httpStatus, String message) {
        super(message, null, false, false);
        this.httpStatus = httpStatus;
    }

    public HttpStatus getHttpStatus() {
        return httpStatus;
    }
}
