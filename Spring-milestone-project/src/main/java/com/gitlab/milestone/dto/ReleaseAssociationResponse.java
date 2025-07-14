package com.gitlab.milestone.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class ReleaseAssociationResponse {
    @Schema(description = "Release ID", example = "10")
    private Long releaseId;

    @Schema(description = "Milestone ID", example = "1")
    private Long milestoneId;

    @Schema(description = "Status", example = "associated")
    private String status;
}