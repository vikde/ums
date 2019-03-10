package com.demo.ums.common.exception;

import com.demo.ums.common.type.JsonResultType;

/**
 * Created on 2017/5/8.
 *
 * @author vikde
 */
public class ApiException extends Exception {
    private JsonResultType jsonResultType;

    public ApiException(JsonResultType jsonResultType) {
        super(jsonResultType.getDefaultMessage(), null, false, false);
        this.jsonResultType = jsonResultType;
    }

    public ApiException(JsonResultType jsonResultType, String message) {
        super(message, null, false, false);
        this.jsonResultType = jsonResultType;
    }

    public JsonResultType getJsonResultType() {
        return jsonResultType;
    }
}
