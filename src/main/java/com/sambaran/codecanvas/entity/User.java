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
public class User {
    long id;
    String email;
    String passwordHash;
    String name;
    String avatarUrl;
    Instant createdAt;
    Instant updatedAt;
    Instant deletedAt; // soft delete - if it is null user is not deleted, if it is not null then user deleted but not from database

}
