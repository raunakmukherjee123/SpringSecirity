package com.example.SecurityPractice.dto;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class PageResponse<T> {
    private T content;
    private boolean isLastPage;
    private int pageNumber;
    private int pageSize;
    private int totalPages;
    private Long totalElements;
}
