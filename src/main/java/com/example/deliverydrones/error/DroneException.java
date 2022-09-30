package com.example.deliverydrones.error;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;

@RequiredArgsConstructor(staticName = "of")
public class DroneException extends RuntimeException {
    private final ErrorCode errorCode;

    public HttpStatus getHttpStatus() {
        return errorCode.getHttpStatus();
    }

    @Override
    public String toString() {
        return "DroneException{" +
                "errorCode=" + errorCode +
                '}';
    }
}
