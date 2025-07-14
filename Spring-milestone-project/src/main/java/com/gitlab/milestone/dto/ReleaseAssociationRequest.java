package com.gitlab.milestone.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotNull;
import lombok.Data;



@Data
public class ReleaseAssociationRequest {
    @NotNull
    @Schema(description = "Milestone ID to associate", example = "1")
    private Long milestoneId;
}