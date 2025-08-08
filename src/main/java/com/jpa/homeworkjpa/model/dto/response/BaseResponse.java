package com.jpa.homeworkjpa.model.dto.response;


import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

public class BaseResponse {
    public <T> ResponseEntity<ApiResponse<T>> responseEntity(T payload , String message , HttpStatus status) {
        ApiResponse<T> apiResponse = ApiResponse.<T>builder()
                .status(status)
                .message(message)
                .payload(payload)
                .build();
        return ResponseEntity.ok(apiResponse);
    }
}
