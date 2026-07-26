package com.sambaran.codecanvas.entity;

import java.time.Instant;

/**
 * @author Sambaran
 */
public class UsageLog {
    Long  id;
    User user;
    Project project;
    String action;
    Integer tokensUsed;
    Integer durationMs;
    String metaData;
    Instant createdAt;
}
