package com.jpa.homeworkjpa.model.dto.response;

import com.jpa.homeworkjpa.model.entity.Product;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ListProductResponse {
    private List<Product> items;
    private Pagination pagination;
}
