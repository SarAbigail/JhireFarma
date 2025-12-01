package com.example.botica.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class GlobalExceptionHandler {

  private static final Logger logger = LoggerFactory.getLogger(GlobalExceptionHandler.class);

  @ExceptionHandler(Exception.class)
  public String handleException(Exception ex) {
    // Guardar el error en los logs
    logger.error("Ocurrió un error:", ex);

    // Redirigir a la página de error
    return "error";
  }
}
