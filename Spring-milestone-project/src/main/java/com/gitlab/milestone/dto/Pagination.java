package com.gitlab.milestone.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class Pagination {
    @Schema(description = "Current page", example = "1")
    private int page;

    @Schema(description = "Page size", example = "20")
    private int size;

    @Schema(description = "Total elements", example = "100")
    private long totalElements;

    @Schema(description = "Total pages", example = "5")
    private int totalPages;
}