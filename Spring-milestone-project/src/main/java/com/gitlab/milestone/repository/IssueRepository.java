package com.gitlab.milestone.repository;

import com.gitlab.milestone.entity.Issue;
import com.gitlab.milestone.entity.Milestone;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface IssueRepository extends JpaRepository<Issue, Long> {
    List<Issue> findByMilestone(Milestone milestone);
    int countByMilestoneAndState(Milestone milestone, String state);
    int countByMilestone(Milestone milestone);
}