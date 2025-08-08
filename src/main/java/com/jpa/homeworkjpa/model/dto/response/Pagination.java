package com.jpa.homeworkjpa.model.dto.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Pagination {
    private Integer totalElements;
    private Integer currentPage;
    private Integer pageSize;
    private Long totalPage;
}
