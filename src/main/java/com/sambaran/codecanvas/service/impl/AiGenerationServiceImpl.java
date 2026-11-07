package com.sambaran.codecanvas.service.impl;

import com.sambaran.codecanvas.service.AiGenerationService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;

/**
 * @author Sambaran
 */
@Service
@RequiredArgsConstructor
public class AiGenerationServiceImpl implements AiGenerationService {
    @Override
    public Flux<String> streamResponse(String message, Long projectId) {
        return null;
    }
}
