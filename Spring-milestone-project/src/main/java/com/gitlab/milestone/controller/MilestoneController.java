package com.gitlab.milestone.controller;

import com.gitlab.milestone.dto.*;
import com.gitlab.milestone.entity.Milestone;
import com.gitlab.milestone.service.*;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/api/v1/milestones")
@RequiredArgsConstructor
@Tag(name = "Milestone API", description = "Milestone management endpoints")
@Validated
public class MilestoneController {

    private final MilestoneService milestoneService;
    private final ReleaseService releaseService;
    private final AnalyticsService analyticsService;
    private final SearchService searchService;
    private final CacheService cacheService;

    @PostMapping
    @Operation(summary = "Create a new milestone")
    public MilestoneResponse createMilestone(@Valid @RequestBody MilestoneCreateRequest request, Authentication authentication) {
        return milestoneService.createMilestone(request, authentication.getName());
    }

    @PatchMapping("/{milestoneId}/close")
    @Operation(summary = "Close a milestone")
    public MilestoneResponse closeMilestone(@PathVariable Long milestoneId, Authentication authentication) {
        return milestoneService.closeMilestone(milestoneId, authentication.getName());
    }

    @PostMapping("/releases/{releaseId}/milestone")
    @Operation(summary = "Associate a release with a milestone")
    public ReleaseAssociationResponse associateRelease(
            @PathVariable Long releaseId,
            @Valid @RequestBody ReleaseAssociationRequest request,
            Authentication authentication) {
        return releaseService.associateReleaseWithMilestone(releaseId, request, authentication.getName());
    }

    @GetMapping("/{milestoneId}/progress")
    @Operation(summary = "View milestone progress")
    public MilestoneProgressResponse getMilestoneProgress(@PathVariable Long milestoneId) {
        // For demo: fetch milestone entity, in real code handle not found
        Milestone milestone = null; // Should fetch from repository
        return cacheService.getMilestoneProgress(milestoneId, milestone);
    }

    @GetMapping("/search")
    @Operation(summary = "Search and filter milestones")
    public MilestoneSearchResponse searchMilestones(
            @RequestParam(required = false) String title,
            @RequestParam(required = false) String state,
            @RequestParam(required = false) String scope,
            @RequestParam(required = false) Long scopeId,
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "20") int size) {
        return searchService.searchMilestones(title, state, scope, scopeId, page, size);
    }

    @GetMapping("/{milestoneId}/analytics")
    @Operation(summary = "Get milestone analytics")
    public MilestoneAnalyticsResponse getAnalytics(@PathVariable Long milestoneId) {
        return analyticsService.getAnalytics(milestoneId);
    }
}
