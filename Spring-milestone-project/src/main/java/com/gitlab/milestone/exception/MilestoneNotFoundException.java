package com.gitlab.milestone.exception;
public class MilestoneNotFoundException extends RuntimeException {
    public MilestoneNotFoundException(String message) { super(message); }
}