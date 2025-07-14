package com.gitlab.milestone.service;

import com.gitlab.milestone.entity.Milestone;
import com.gitlab.milestone.entity.User;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

@Service
public class NotificationService {
    private static final Logger logger = LoggerFactory.getLogger(NotificationService.class);

    public void notifyCreation(Milestone milestone, User user) {
        // Send notification or webhook
        logger.info("Notification: Milestone {} created by {}", milestone.getId(), user.getUsername());
    }

    public void notifyClosure(Milestone milestone, User user) {
        // Send notification or webhook
        logger.info("Notification: Milestone {} closed by {}", milestone.getId(), user.getUsername());
    }
}
