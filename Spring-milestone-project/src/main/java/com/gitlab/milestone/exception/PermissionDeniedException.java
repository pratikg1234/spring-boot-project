package com.gitlab.milestone.exception;
public class PermissionDeniedException extends RuntimeException {
    public PermissionDeniedException(String message) { super(message); }
}
