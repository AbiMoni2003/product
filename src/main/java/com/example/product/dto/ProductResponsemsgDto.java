package com.example.product.dto;


public class ProductResponsemsgDto {
    private String message;

    public ProductResponsemsgDto(String message) {
        this.message = message;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
