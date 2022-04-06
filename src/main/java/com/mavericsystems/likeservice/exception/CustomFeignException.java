package com.mavericsystems.likeservice.exception;


public class CustomFeignException extends RuntimeException {
    public CustomFeignException(String s) {
        super(s);
    }
}
