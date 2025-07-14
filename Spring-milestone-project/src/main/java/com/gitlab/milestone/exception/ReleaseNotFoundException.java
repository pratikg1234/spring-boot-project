package com.gitlab.milestone.exception;
public class ReleaseNotFoundException extends RuntimeException {
    public ReleaseNotFoundException(String message) { super(message); }
}