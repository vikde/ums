package com.demo.ums.controller;

import com.demo.ums.common.exception.NotFoundApiException;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * Created on 2017/07/02.
 *
 * @author vikde
 */
@ApiController
public class ApiCommonController {

    @RequestMapping("/api/**")
    public void api() throws NotFoundApiException {
        throw new NotFoundApiException();
    }
}
