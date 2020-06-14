package com.demo.ums.controller;

import com.demo.ums.common.JsonResult;
import com.demo.ums.common.exception.ApiException;
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

import javax.servlet.http.HttpServletResponse;
import java.util.List;

/**
 * @author vikde
 * @date 2020/06/13
 */
@ControllerAdvice
public class ApiExceptionController {

    @ExceptionHandler
    @ResponseBody
    public JsonResult handleException(ApiException exception, HttpServletResponse response) {
        response.setStatus(exception.getHttpStatus().value());
        return JsonResult.getRequestExceptionInstance(exception.getMessage());
    }

    @ExceptionHandler
    @ResponseBody
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public JsonResult handleException(BindException exception) {
        if (exception.hasErrors()) {
            List<ObjectError> objectErrorList = exception.getAllErrors();
            if (objectErrorList.size() > 0) {
                return JsonResult.getRequestExceptionInstance("参数异常:" + objectErrorList.get(0).getDefaultMessage());
            }
        }
        return JsonResult.getRequestExceptionInstance("参数异常");
    }

    @ExceptionHandler
    @ResponseBody
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public JsonResult handleException(MethodArgumentTypeMismatchException exception) {
        return JsonResult.getRequestExceptionInstance("参数异常:" + exception.getMessage());
    }

    @ExceptionHandler
    @ResponseBody
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public JsonResult handleException(MissingServletRequestParameterException exception) {
        return JsonResult.getRequestExceptionInstance("参数异常:" + exception.getMessage());
    }

    @ExceptionHandler
    @ResponseBody
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public JsonResult handleException(AccessDeniedException exception) {
        return JsonResult.getRequestExceptionInstance("无权进行访问");
    }

    @ExceptionHandler
    @ResponseBody
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public JsonResult handleException(Exception exception) {
        return JsonResult.getServiceExceptionInstance(exception.getMessage());
    }
}
