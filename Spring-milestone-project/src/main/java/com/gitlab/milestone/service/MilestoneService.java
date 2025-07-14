package com.gitlab.milestone.service;

import com.gitlab.milestone.dto.*;
import com.gitlab.milestone.entity.Milestone;
import com.gitlab.milestone.entity.User;
import com.gitlab.milestone.exception.*;
import com.gitlab.milestone.repository.MilestoneRepository;
import com.gitlab.milestone.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class MilestoneService {
    private static final Logger logger = LoggerFactory.getLogger(MilestoneService.class);

    private final MilestoneRepository milestoneRepository;
    private final UserRepository userRepository;
    private final NotificationService notificationService;
    private final AuditLogService auditLogService;
    private final PermissionService permissionService;

    @Transactional
    public MilestoneResponse createMilestone(MilestoneCreateRequest request, String username) {
        // Validate permission
        User user = userRepository.findByUsername(username)
                .orElseThrow(() -> new PermissionDeniedException("User not found"));

        if (!permissionService.canCreateMilestone(user, request.getScope(), request.getScopeId())) {
            throw new PermissionDeniedException("Permission denied.");
        }

        // Validate unique title
        milestoneRepository.findByTitleAndScopeAndScopeId(request.getTitle(), request.getScope(), request.getScopeId())
                .ifPresent(m -> { throw new MilestoneTitleNotUniqueException("Milestone title must be unique."); });

        // Validate date range
        if (request.getStartDate().isAfter(request.getDueDate())) {
            throw new InvalidDateRangeException("Start date must be before or equal to due date.");
        }

        Milestone milestone = Milestone.builder()
                .title(request.getTitle())
                .description(request.getDescription())
                .startDate(request.getStartDate())
                .dueDate(request.getDueDate())
                .state("active")
                .scope(request.getScope())
                .scopeId(request.getScopeId())
                .build();

        Milestone saved = milestoneRepository.save(milestone);

        notificationService.notifyCreation(saved, user);
        auditLogService.logCreation(saved, user);

        logger.info("Milestone created: {} by {}", saved.getId(), user.getUsername());

        return MilestoneResponse.builder()
                .id(saved.getId())
                .title(saved.getTitle())
                .description(saved.getDescription())
                .startDate(saved.getStartDate())
                .dueDate(saved.getDueDate())
                .state(saved.getState())
                .scope(saved.getScope())
                .scopeId(saved.getScopeId())
                .build();
    }

    @Transactional
    public MilestoneResponse closeMilestone(Long milestoneId, String username) {
        Milestone milestone = milestoneRepository.findById(milestoneId)
                .orElseThrow(() -> new MilestoneNotFoundException("Milestone not found."));

        if ("closed".equals(milestone.getState())) {
            throw new MilestoneAlreadyClosedException("Milestone already closed.");
        }

        User user = userRepository.findByUsername(username)
                .orElseThrow(() -> new PermissionDeniedException("User not found"));

        if (!permissionService.canCloseMilestone(user, milestone)) {
            throw new PermissionDeniedException("Permission denied.");
        }

        milestone.setState("closed");
        milestoneRepository.save(milestone);

        notificationService.notifyClosure(milestone, user);
        auditLogService.logClosure(milestone, user);

        logger.info("Milestone closed: {} by {}", milestone.getId(), user.getUsername());

        return MilestoneResponse.builder()
                .id(milestone.getId())
                .title(milestone.getTitle())
                .description(milestone.getDescription())
                .startDate(milestone.getStartDate())
                .dueDate(milestone.getDueDate())
                .state(milestone.getState())
                .scope(milestone.getScope())
                .scopeId(milestone.getScopeId())
                .build();
    }
}
