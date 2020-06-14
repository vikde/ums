package com.demo.ums.controller;

import com.demo.ums.common.exception.ApiException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created on 2017/07/02.
 *
 * @author vikde
 */
@RestController
public class ApiCommonController {
    /**
     * 不存在的api
     */
    @RequestMapping("/api/**")
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public void api() throws ApiException {
        throw new ApiException(HttpStatus.NOT_FOUND);
    }
}
