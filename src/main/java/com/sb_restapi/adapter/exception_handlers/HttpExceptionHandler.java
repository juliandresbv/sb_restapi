package com.sb_restapi.adapter.exception_handlers;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.context.request.WebRequest;

import com.sb_restapi.adapter.exceptions.HttpException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.boot.web.error.ErrorAttributeOptions;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.ServletWebRequest;
import org.springframework.boot.web.servlet.error.DefaultErrorAttributes;

import java.util.Map;

@ControllerAdvice
public class HttpExceptionHandler {
  @Autowired
  private DefaultErrorAttributes defaultErrorAttributes;

  @ExceptionHandler(HttpException.class)
  public ResponseEntity<Map<String, Object>> handleHttpException(HttpException e, WebRequest request) {
    Map<String, Object> errorAttributes = this.defaultErrorAttributes
        .getErrorAttributes(request, ErrorAttributeOptions.defaults());

    HttpStatus httpStatus = e.getStatus();

    String error = httpStatus.getReasonPhrase();
    String message = e.getMessage();
    String path = ((ServletWebRequest) request).getRequest().getRequestURI();
    int status = httpStatus.value();

    errorAttributes.put("error", error);
    errorAttributes.put("message", message);
    errorAttributes.put("path", path);
    errorAttributes.put("status", status);

    return new ResponseEntity<>(errorAttributes, httpStatus);
  }
}
