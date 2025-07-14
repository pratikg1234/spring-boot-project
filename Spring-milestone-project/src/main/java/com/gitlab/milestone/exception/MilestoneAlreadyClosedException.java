package com.gitlab.milestone.exception;
public class MilestoneAlreadyClosedException extends RuntimeException {
    public MilestoneAlreadyClosedException(String message) { super(message); }
}
