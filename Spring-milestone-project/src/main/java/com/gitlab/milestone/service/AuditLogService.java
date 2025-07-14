package com.gitlab.milestone.service;

import com.gitlab.milestone.entity.Milestone;
import com.gitlab.milestone.entity.Release;
import com.gitlab.milestone.entity.User;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

@Service
public class AuditLogService {
    private static final Logger logger = LoggerFactory.getLogger(AuditLogService.class);

    public void logCreation(Milestone milestone, User user) {
        logger.info("AUDIT: Milestone {} created by {} at {}", milestone.getId(), user.getUsername(), System.currentTimeMillis());
    }

    public void logClosure(Milestone milestone, User user) {
        logger.info("AUDIT: Milestone {} closed by {} at {}", milestone.getId(), user.getUsername(), System.currentTimeMillis());
    }

    public void logReleaseAssociation(Release release, Milestone milestone, User user) {
        logger.info("AUDIT: Release {} associated with milestone {} by {} at {}", release.getId(), milestone.getId(), user.getUsername(), System.currentTimeMillis());
    }
}
