package com.demo.ums.controller;

import com.demo.ums.common.JsonResult;
import com.demo.ums.common.exception.ApiException;
import com.demo.ums.common.exception.NotFoundApiException;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.validation.BindException;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
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
    @ResponseBody
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public JsonResult handleException(NotFoundApiException exception) {
        return JsonResult.getInstance(exception.getHttpStatus(), exception.getMessage());
    }

    @ExceptionHandler
    @ResponseBody
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public JsonResult handleException(ApiException exception) {
        return JsonResult.getInstance(exception.getHttpStatus(), exception.getMessage());
    }

    @ExceptionHandler
    @ResponseBody
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public JsonResult handleException(BindException exception) {
        if (exception.hasErrors()) {
            List<ObjectError> objectErrorList = exception.getAllErrors();
            if (objectErrorList.size() > 0) {
                return JsonResult.getInstance(HttpStatus.BAD_REQUEST, "参数异常:" + objectErrorList.get(0).getDefaultMessage());
            }
        }
        return JsonResult.getInstance(HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler
    @ResponseBody
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public JsonResult handleException(MethodArgumentTypeMismatchException exception) {
        return JsonResult.getInstance(HttpStatus.BAD_REQUEST, "参数异常:" + exception.getMessage());
    }

    @ExceptionHandler
    @ResponseBody
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public JsonResult handleException(MissingServletRequestParameterException exception) {
        return JsonResult.getInstance(HttpStatus.BAD_REQUEST, "参数异常:" + exception.getMessage());
    }

    @ExceptionHandler
    @ResponseBody
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public JsonResult handleException(AccessDeniedException exception) {
        return JsonResult.getInstance(HttpStatus.BAD_REQUEST, "无权进行访问");
    }

    @ExceptionHandler
    @ResponseBody
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public JsonResult handleException(Exception exception) {
        return JsonResult.getInstance(HttpStatus.INTERNAL_SERVER_ERROR, exception.getMessage());
    }
}
