package com.sambaran.codecanvas.entity;

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
public class Plan {
    Long id;
    String name;
    String stripePriceId;
    Integer maxProject;
    Integer maxTokensPerDay;
    Integer maxPreview;
    Boolean unlimitedAi;
    Boolean active;
}
