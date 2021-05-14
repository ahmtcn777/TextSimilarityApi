package com.otosor.textSearch.ExceptionHandler;

import com.otosor.textSearch.AppResponse;
import com.otosor.textSearch.Exceptions.CustomException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@ControllerAdvice
public class GlobalControllerExceptionAdvice {

    @ExceptionHandler(value = CustomException.class)
    @ResponseBody
    public Object exceptionHandler (Exception e, HttpServletRequest request, HttpServletResponse response) throws Exception {
        return AppResponse.error(((CustomException) e).getMessage());
    }
}
