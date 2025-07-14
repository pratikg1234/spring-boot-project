package com.gitlab.milestone.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import lombok.Data;
import java.time.LocalDate;

@Data
public class MilestoneCreateRequest {
    @NotBlank
    @Schema(description = "Milestone title", example = "Sprint 1")
    private String title;

    @Schema(description = "Milestone description", example = "First sprint of Q1")
    private String description;

    @NotNull
    @Schema(description = "Start date (YYYY-MM-DD)", example = "2024-07-01")
    private LocalDate startDate;

    @NotNull
    @Schema(description = "Due date (YYYY-MM-DD)", example = "2024-07-15")
    private LocalDate dueDate;

    @NotBlank
    @Pattern(regexp = "project|group")
    @Schema(description = "Scope: project or group", example = "project")
    private String scope;

    @NotNull
    @Schema(description = "Scope ID", example = "42")
    private Long scopeId;
}