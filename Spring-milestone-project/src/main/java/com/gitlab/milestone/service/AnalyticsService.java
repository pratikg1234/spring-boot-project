package com.gitlab.milestone.service;

import com.gitlab.milestone.dto.MilestoneAnalyticsResponse;
import com.gitlab.milestone.entity.Milestone;
import com.gitlab.milestone.entity.Issue;
import com.gitlab.milestone.exception.AnalyticsDataUnavailableException;
import com.gitlab.milestone.repository.IssueRepository;
import com.gitlab.milestone.repository.MilestoneRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class AnalyticsService {

    private final MilestoneRepository milestoneRepository;
    private final IssueRepository issueRepository;

    @Cacheable(value = "milestoneAnalytics", key = "#milestoneId")
    public MilestoneAnalyticsResponse getAnalytics(Long milestoneId) {
        Milestone milestone = milestoneRepository.findById(milestoneId)
                .orElseThrow(() -> new AnalyticsDataUnavailableException("Milestone not found."));

        List<Issue> issues = issueRepository.findByMilestone(milestone);
        if (issues.isEmpty()) {
            throw new AnalyticsDataUnavailableException("No issues found for milestone.");
        }

        long completed = issues.stream().filter(i -> "closed".equals(i.getState())).count();
        double completionRate = (double) completed / issues.size();

        double avgCompletionTime = issues.stream()
                .filter(i -> "closed".equals(i.getState()))
                .mapToDouble(i -> 2.0) // Placeholder: replace with actual time calculation
                .average().orElse(0);

        return MilestoneAnalyticsResponse.builder()
                .milestoneId(milestoneId)
                .completionRate(completionRate)
                .avgCompletionTime(avgCompletionTime)
                .build();
    }
}
