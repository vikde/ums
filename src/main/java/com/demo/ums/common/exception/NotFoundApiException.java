package com.demo.ums.common.exception;

import com.demo.ums.common.type.JsonResultType;

/**
 * Created on 2017/7/3.
 *
 * @author vikde
 */
public class NotFoundApiException extends ApiException {
    public NotFoundApiException() {
        super(JsonResultType.SYSTEM_API_NOT_FOUND);
    }
}
