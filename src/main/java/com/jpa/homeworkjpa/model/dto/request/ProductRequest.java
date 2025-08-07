package com.jpa.homeworkjpa.model.dto.request;

import jakarta.validation.constraints.*;
import lombok.*;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ProductRequest {
    @NotNull
    @Size(min = 1, max = 150, message = "name must be [1-150] characters")
    private String name;

    @NotNull
    @DecimalMin(value = "0.0", inclusive = false, message = "Price must be greater than 0")
    private Double price;

    @NotNull
    @Min(value = 0, message = "Quantity cannot be less than 0")
    @Max(value = 99999, message = "Quantity cannot be greater than 99999")
    private Integer quantity;
}
