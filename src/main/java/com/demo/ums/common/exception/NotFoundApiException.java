package com.demo.ums.common.exception;

import org.springframework.http.HttpStatus;

/**
 * Created on 2017/7/3.
 *
 * @author vikde
 */
public class NotFoundApiException extends ApiException {
    public NotFoundApiException() {
        super(HttpStatus.NOT_FOUND);
    }
}
