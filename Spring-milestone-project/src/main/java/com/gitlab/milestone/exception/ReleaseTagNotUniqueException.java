package com.gitlab.milestone.exception;
public class ReleaseTagNotUniqueException extends RuntimeException {
    public ReleaseTagNotUniqueException(String message) { super(message); }
}
