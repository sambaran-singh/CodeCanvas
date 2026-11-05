package com.sambaran.codecanvas.entity;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.FieldDefaults;

/**
 * @author Sambaran
 */

@FieldDefaults(level = AccessLevel.PRIVATE)
@Getter
@Setter
@Entity
public class Plan {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;

    String name;
    @Column(unique = true)
    String stripePriceId;

    Integer maxProject;

    Integer maxTokensPerDay;

    Integer maxPreview;

    Boolean unlimitedAi;
    
    Boolean active;
}
