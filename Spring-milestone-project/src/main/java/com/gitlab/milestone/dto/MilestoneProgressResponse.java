package com.gitlab.milestone.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class MilestoneProgressResponse {
    @Schema(description = "Milestone ID", example = "1")
    private Long milestoneId;

    @Schema(description = "Number of completed issues", example = "5")
    private int completedIssues;

    @Schema(description = "Total number of issues", example = "10")
    private int totalIssues;

    @Schema(description = "Percent complete", example = "50")
    private int percentComplete;
}