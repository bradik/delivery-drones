package com.example.deliverydrones.error;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;

@Getter
@RequiredArgsConstructor
public enum ErrorCode {
    COM_01("COM_01", HttpStatus.BAD_REQUEST, "Common error"),
    FRS_01("FRS_01", HttpStatus.BAD_REQUEST, "Drone is already registered"),
    FRS_02("FRS_02", HttpStatus.BAD_REQUEST, "Flight id not found"),
    FRS_03("FRS_02", HttpStatus.BAD_REQUEST, "Exceeded weight limit"),
    DR_01("DR_01", HttpStatus.BAD_REQUEST, "drone with serial number not found"),
    ;
    private final String code;
    private final HttpStatus httpStatus;
    private final String message;

    @Override
    public String toString() {
        return "ErrorCode{" +
                "code='" + code + '\'' +
                ", message='" + message + '\'' +
                '}';
    }
}
