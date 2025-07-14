package com.gitlab.milestone.service;

import com.gitlab.milestone.dto.MilestoneProgressResponse;
import com.gitlab.milestone.entity.Milestone;
import com.gitlab.milestone.repository.IssueRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CacheService {

    private final IssueRepository issueRepository;

    @Cacheable(value = "milestoneProgress", key = "#milestoneId")
    public MilestoneProgressResponse getMilestoneProgress(Long milestoneId, Milestone milestone) {
        int total = issueRepository.countByMilestone(milestone);
        int completed = issueRepository.countByMilestoneAndState(milestone, "closed");
        int percent = total == 0 ? 0 : (completed * 100) / total;
        return MilestoneProgressResponse.builder()
                .milestoneId(milestoneId)
                .completedIssues(completed)
                .totalIssues(total)
                .percentComplete(percent)
                .build();
    }
}
