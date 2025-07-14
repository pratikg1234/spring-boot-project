package com.gitlab.milestone.service;

import com.gitlab.milestone.entity.Milestone;
import com.gitlab.milestone.entity.User;
import org.springframework.stereotype.Service;

@Service
public class PermissionService {
    public boolean canCreateMilestone(User user, String scope, Long scopeId) {
        // Implement RBAC logic
        return user.getRoles().contains("ROLE_ADMIN") || user.getRoles().contains("ROLE_PROJECT_MANAGER");
    }

    public boolean canCloseMilestone(User user, Milestone milestone) {
        // Implement RBAC logic
        return user.getRoles().contains("ROLE_ADMIN") || user.getRoles().contains("ROLE_PROJECT_MANAGER");
    }

    public boolean canAssociateRelease(User user, Milestone milestone) {
        // Implement RBAC logic
        return user.getRoles().contains("ROLE_ADMIN") || user.getRoles().contains("ROLE_PROJECT_MANAGER");
    }
}
