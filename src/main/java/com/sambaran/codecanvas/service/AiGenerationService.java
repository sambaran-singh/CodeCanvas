package com.sambaran.codecanvas.service;

import aj.org.objectweb.asm.commons.Remapper;
import org.springframework.http.codec.ServerSentEvent;
import reactor.core.publisher.Flux;

/**
 * @author Sambaran
 */
public interface AiGenerationService {

    Flux<String> streamResponse(String message, Long projectId);
}
