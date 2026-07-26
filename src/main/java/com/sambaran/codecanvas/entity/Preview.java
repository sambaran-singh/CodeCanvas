package com.sambaran.codecanvas.entity;

import com.sambaran.codecanvas.enums.PreviewStatus;
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
public class Preview {

    Long id;
    Project project;
    String namespace;
    String podName;
    String previewUrl;
    Instant startedAt;
    Instant terminatedAt;
    Instant createdAt;
    PreviewStatus status;
}
