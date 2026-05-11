package com.example.validationframework.dto;

import com.example.validationframework.annotation.Max;
import com.example.validationframework.annotation.MaxLength;
import com.example.validationframework.annotation.Min;
import com.example.validationframework.annotation.MinLength;
import com.example.validationframework.annotation.NotBlank;
import com.example.validationframework.annotation.NotNull;
import com.example.validationframework.annotation.Range;

public class ProductForm {

    @NotBlank
    @MinLength(value = 2)
    @MaxLength(value = 100)
    private String name;

    @NotBlank
    @MinLength(value = 10, message = "Description must have at least 10 characters")
    @MaxLength(value = 500)
    private String description;

    @NotNull
    @Min(value = 0.01, message = "Price must be greater than zero")
    @Max(value = 999999.99, message = "Price must not exceed 999,999.99")
    private String price;

    @NotNull
    @Range(min = 0, max = 10000, message = "Stock must be between 0 and 10,000")
    private String stock;

    public String getName() { return name; }
    public void setName(String name) { this.name = name; }

    public String getDescription() { return description; }
    public void setDescription(String description) { this.description = description; }

    public String getPrice() { return price; }
    public void setPrice(String price) { this.price = price; }

    public String getStock() { return stock; }
    public void setStock(String stock) { this.stock = stock; }
}
