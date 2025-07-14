package com.gitlab.milestone.util;

import org.slf4j.MDC;

import java.util.UUID;

public class CorrelationIdUtil {
    private static final String CORRELATION_ID = "correlationId";

    public static void setCorrelationId() {
        String correlationId = UUID.randomUUID().toString();
        MDC.put(CORRELATION_ID, correlationId);
    }

    public static String getCorrelationId() {
        return MDC.get(CORRELATION_ID);
    }
}
