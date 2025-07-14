package com.gitlab.milestone.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class MilestoneAnalyticsResponse {
    @Schema(description = "Milestone ID", example = "1")
    private Long milestoneId;

    @Schema(description = "Completion rate", example = "0.8")
    private double completionRate;

    @Schema(description = "Average completion time", example = "5.2")
    private double avgCompletionTime;
}
