package com.gitlab.milestone.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Builder;
import lombok.Data;

import java.util.List;

@Data
@Builder
public class MilestoneSearchResponse {
    @Schema(description = "Search results")
    private List<MilestoneResponse> results;

    @Schema(description = "Pagination info")
    private Pagination pagination;
}