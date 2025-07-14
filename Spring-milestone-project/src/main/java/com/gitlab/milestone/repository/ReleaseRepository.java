package com.gitlab.milestone.repository;

import com.gitlab.milestone.entity.Release;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ReleaseRepository extends JpaRepository<Release, Long> {
    Optional<Release> findByTagAndMilestone_Id(String tag, Long milestoneId);
    Optional<Release> findById(Long id);
}