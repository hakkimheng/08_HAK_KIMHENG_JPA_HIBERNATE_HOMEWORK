package com.jpa.homeworkjpa.model.dto.response;

import com.jpa.homeworkjpa.Application;
import com.jpa.homeworkjpa.model.entity.Product;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

public class BaseResponse {
    public <T> ResponseEntity<ApiResponse<T>> responseEntity(T payload , String message , HttpStatus status) {
        ApiResponse<T> apiResponse = ApiResponse.<T>builder()
                .success(true)
                .status(status)
                .message(message)
                .payload(payload)
                .build();
        return ResponseEntity.ok(apiResponse);
    }
}
