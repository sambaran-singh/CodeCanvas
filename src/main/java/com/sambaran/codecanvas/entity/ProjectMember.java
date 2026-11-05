package com.sambaran.codecanvas.entity;

import com.sambaran.codecanvas.enums.ProjectRole;
import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.FieldDefaults;

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
@Table(name = "project_members")
public class ProjectMember {
    @EmbeddedId
    ProjectMemberId id;

    @ManyToOne
     @MapsId("projectId")
    Project project;
    @ManyToOne
    @MapsId("userId")
    User user;

    @Enumerated(EnumType.STRING)
    ProjectRole projectRole;
    Instant invitedAt;
    Instant acceptedAt;
}
