package com.jpa.homeworkjpa.model.dto.response;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Builder;
import lombok.Getter;
import org.springframework.http.HttpStatus;

import java.time.LocalDateTime;

@Getter
@Builder
@JsonInclude(JsonInclude.Include.NON_NULL)
public class ApiResponse<T> {
    private Boolean success;
    private HttpStatus status;
    private String message;
    private T payload;
    private final LocalDateTime timestamp = LocalDateTime.now();
}
