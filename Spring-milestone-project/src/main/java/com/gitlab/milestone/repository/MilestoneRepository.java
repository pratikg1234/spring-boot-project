package com.gitlab.milestone.repository;

import com.gitlab.milestone.entity.Milestone;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.List;

@Repository
public interface MilestoneRepository extends JpaRepository<Milestone, Long> {
    Optional<Milestone> findByTitleAndScopeAndScopeId(String title, String scope, Long scopeId);
    List<Milestone> findByScopeAndScopeId(String scope, Long scopeId);
    // Add custom queries for search/filter as needed
}