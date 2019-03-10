package com.demo.ums.controller;

import com.demo.ums.common.JsonResult;
import com.demo.ums.common.exception.ApiException;
import com.demo.ums.common.exception.NotFoundApiException;
import com.demo.ums.common.type.JsonResultType;
import org.springframework.validation.BindException;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;

import java.util.List;

/**
 * Created on 2017/2/21.
 *
 * @author vikde
 */
@ControllerAdvice
public class ApiExceptionController {

    @ExceptionHandler
    @ApiResponseBody
    public JsonResult handleException(NotFoundApiException exception) {
        return JsonResult.getInstance(exception.getJsonResultType(), exception.getMessage());
    }

    @ExceptionHandler
    @ApiResponseBody
    public JsonResult handleException(ApiException exception) {
        return JsonResult.getInstance(exception.getJsonResultType(), exception.getMessage());
    }

    @ExceptionHandler
    @ApiResponseBody
    public JsonResult handleException(BindException exception) {
        if (exception.hasErrors()) {
            List<ObjectError> objectErrorList = exception.getAllErrors();
            if (objectErrorList.size() > 0) {
                return JsonResult.getInstance(JsonResultType.SYSTEM_API_PARAMETER_EXCEPTION,
                                              JsonResultType.SYSTEM_API_PARAMETER_EXCEPTION.getDefaultMessage() + ":" + objectErrorList.get(0).getDefaultMessage());
            }
        }
        return JsonResult.getInstance(JsonResultType.SYSTEM_API_PARAMETER_EXCEPTION);
    }

    @ExceptionHandler
    @ApiResponseBody
    public JsonResult handleException(MethodArgumentTypeMismatchException exception) {
        return JsonResult.getInstance(JsonResultType.SYSTEM_API_PARAMETER_EXCEPTION,
                                      JsonResultType.SYSTEM_API_PARAMETER_EXCEPTION.getDefaultMessage() + ":" + exception.getMessage());
    }

    @ExceptionHandler
    @ApiResponseBody
    public JsonResult handleException(MissingServletRequestParameterException exception) {
        return JsonResult.getInstance(JsonResultType.SYSTEM_API_PARAMETER_EXCEPTION,
                                      JsonResultType.SYSTEM_API_PARAMETER_EXCEPTION.getDefaultMessage() + ":" + exception.getMessage());
    }

    @ExceptionHandler
    @ApiResponseBody
    public JsonResult handleException(Exception exception) {
        return JsonResult.getInstance(JsonResultType.SYSTEM_EXCEPTION,
                                      JsonResultType.SYSTEM_EXCEPTION.getDefaultMessage() + ":" + exception.getMessage());
    }
}
