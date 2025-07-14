package com.gitlab.milestone.service;

import com.gitlab.milestone.dto.*;
import com.gitlab.milestone.entity.Milestone;
import com.gitlab.milestone.entity.Release;
import com.gitlab.milestone.entity.User;
import com.gitlab.milestone.exception.*;
import com.gitlab.milestone.repository.MilestoneRepository;
import com.gitlab.milestone.repository.ReleaseRepository;
import com.gitlab.milestone.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class ReleaseService {
    private static final Logger logger = LoggerFactory.getLogger(ReleaseService.class);

    private final ReleaseRepository releaseRepository;
    private final MilestoneRepository milestoneRepository;
    private final UserRepository userRepository;
    private final PermissionService permissionService;
    private final AuditLogService auditLogService;

    @Transactional
    public ReleaseAssociationResponse associateReleaseWithMilestone(Long releaseId, ReleaseAssociationRequest request, String username) {
        Release release = releaseRepository.findById(releaseId)
                .orElseThrow(() -> new ReleaseNotFoundException("Release not found."));

        Milestone milestone = milestoneRepository.findById(request.getMilestoneId())
                .orElseThrow(() -> new MilestoneNotFoundException("Milestone not found."));

        if (release.getMilestone() != null && !release.getMilestone().getId().equals(milestone.getId())) {
            throw new AssociationConflictException("Release already associated with another milestone.");
        }

        User user = userRepository.findByUsername(username)
                .orElseThrow(() -> new PermissionDeniedException("User not found"));

        if (!permissionService.canAssociateRelease(user, milestone)) {
            throw new PermissionDeniedException("Permission denied.");
        }

        release.setMilestone(milestone);
        releaseRepository.save(release);

        auditLogService.logReleaseAssociation(release, milestone, user);

        logger.info("Release {} associated with milestone {} by {}", release.getId(), milestone.getId(), user.getUsername());

        return ReleaseAssociationResponse.builder()
                .releaseId(release.getId())
                .milestoneId(milestone.getId())
                .status("associated")
                .build();
    }
}
