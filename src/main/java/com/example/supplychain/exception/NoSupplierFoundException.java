package com.example.supplychain.exception;

public class NoSupplierFoundException extends RuntimeException {

    public NoSupplierFoundException() {
    }

    public NoSupplierFoundException(String message) {
        super(message);
    }
}
