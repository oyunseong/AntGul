package com.antgul.antgul_android.util;

public class ValidationResult {
    public String message;
    public boolean isSuccess;

    ValidationResult(String message, boolean isSuccess) {
        this.message = message;
        this.isSuccess = isSuccess;
    }
}