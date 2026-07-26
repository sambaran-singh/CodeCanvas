package com.sambaran.codecanvas.entity;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.FieldDefaults;

import java.time.Instant;

/**
 * @author Sambaran
 */
@FieldDefaults(level = AccessLevel.PRIVATE)
@Getter
@Setter
public class Project {
    Long id;
    String name;
    User owner;
    Boolean isPublic = false;
    Instant createdAt;
    Instant updatedAt;
}
