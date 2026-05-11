package com.example.validationframework.controller;

import com.example.validationframework.dto.ProductForm;
import com.example.validationframework.dto.UserRegistrationForm;
import com.example.validationframework.service.ValidationService;
import com.example.validationframework.validator.core.ValidationResult;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class DemoController {

    private final ValidationService validationService;

    public DemoController(ValidationService validationService) {
        this.validationService = validationService;
    }

    @GetMapping("/")
    public String index() {
        return "index";
    }

    @GetMapping("/registration")
    public String registrationForm(Model model) {
        model.addAttribute("form", new UserRegistrationForm());
        model.addAttribute("errors", java.util.Collections.emptyList());
        return "registration";
    }

    @PostMapping("/registration")
    public String submitRegistration(@ModelAttribute UserRegistrationForm form, Model model) {
        ValidationResult result = validationService.validate(form);
        if (result.isValid()) {
            model.addAttribute("success", true);
            model.addAttribute("errors", java.util.Collections.emptyList());
            model.addAttribute("backUrl", "/registration");
            return "result";
        }
        model.addAttribute("form", form);
        model.addAttribute("errors", result.getErrors());
        return "registration";
    }

    @GetMapping("/product")
    public String productForm(Model model) {
        model.addAttribute("form", new ProductForm());
        model.addAttribute("errors", java.util.Collections.emptyList());
        return "product";
    }

    @PostMapping("/product")
    public String submitProduct(@ModelAttribute ProductForm form, Model model) {
        ValidationResult result = validationService.validate(form);
        if (result.isValid()) {
            model.addAttribute("success", true);
            model.addAttribute("errors", java.util.Collections.emptyList());
            model.addAttribute("backUrl", "/product");
            return "result";
        }
        model.addAttribute("form", form);
        model.addAttribute("errors", result.getErrors());
        return "product";
    }
}
