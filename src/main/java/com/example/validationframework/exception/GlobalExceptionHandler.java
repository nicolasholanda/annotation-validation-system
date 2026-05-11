package com.example.validationframework.exception;

import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(ValidationException.class)
    public String handleValidationException(ValidationException ex, Model model) {
        model.addAttribute("success", false);
        model.addAttribute("errors", ex.getResult().getErrors());
        model.addAttribute("backUrl", "/");
        return "result";
    }

    @ExceptionHandler(Exception.class)
    public String handleGenericException(Exception ex, Model model) {
        model.addAttribute("success", false);
        model.addAttribute("errors", java.util.Collections.emptyList());
        model.addAttribute("errorMessage", "An unexpected error occurred: " + ex.getMessage());
        model.addAttribute("backUrl", "/");
        return "result";
    }
}
