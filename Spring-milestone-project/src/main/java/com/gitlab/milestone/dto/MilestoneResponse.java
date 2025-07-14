package com.gitlab.milestone.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Builder;
import lombok.Data;

import java.time.LocalDate;

@Data
@Builder
public class MilestoneResponse {
    @Schema(description = "Milestone ID", example = "1")
    private Long id;

    @Schema(description = "Milestone title", example = "Sprint 1")
    private String title;

    @Schema(description = "Milestone description", example = "First sprint of Q1")
    private String description;

    @Schema(description = "Start date", example = "2024-07-01")
    private LocalDate startDate;

    @Schema(description = "Due date", example = "2024-07-15")
    private LocalDate dueDate;

    @Schema(description = "State", example = "active")
    private String state;

    @Schema(description = "Scope", example = "project")
    private String scope;

    @Schema(description = "Scope ID", example = "42")
    private Long scopeId;
}