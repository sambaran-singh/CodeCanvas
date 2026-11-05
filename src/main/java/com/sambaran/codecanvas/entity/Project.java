package com.sambaran.codecanvas.entity;

import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.FieldDefaults;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.time.Instant;

/**
 * @author Sambaran
 */
@FieldDefaults(level = AccessLevel.PRIVATE)
@Getter
@Setter
@Entity
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Table(name = "projects",
        indexes = {
        @Index(name = "idx_projects_updated_at_desc", columnList = "updated_at ,deleted_at"),
        @Index(name = "idx_projects_deleted_at_updated_at_desc",columnList ="deleted_at ,updated_at"),
        @Index(name="idx_projects_deleted_at", columnList = "deleted_at")
        }
)
public class Project {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;
    String name;
    Boolean isPublic = false;

    @CreationTimestamp
    Instant createdAt;

    @UpdateTimestamp
    Instant updatedAt;

    Instant deletedAt;

}
