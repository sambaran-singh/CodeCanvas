package com.sambaran.codecanvas.service.impl;

import com.sambaran.codecanvas.llm.PromptUtils;
import com.sambaran.codecanvas.llm.advisors.FileTreeContextAdvisor;
import com.sambaran.codecanvas.llm.tools.CodeGenerationTools;
import com.sambaran.codecanvas.security.AuthUtil;
import com.sambaran.codecanvas.service.AiGenerationService;
import com.sambaran.codecanvas.service.ProjectFileService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.ai.chat.client.ChatClient;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.scheduler.Scheduler;
import reactor.core.scheduler.Schedulers;

import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @author Sambaran
 */
@Service
@RequiredArgsConstructor
@Slf4j
public class AiGenerationServiceImpl implements AiGenerationService {
    private final AuthUtil authUtil;
    private final ChatClient chatClient;
    private final ProjectFileService projectFileService;
    private final FileTreeContextAdvisor fileTreeContextAdvisor;

    private static final Pattern FILE_TAG_PATTERN = Pattern.compile("<file path=\"([^\"]+)\">(.*?)</file>", Pattern.DOTALL);
    @Override
    @PreAuthorize("@security.canEditProject(#projectId)")
    public Flux<String> streamResponse(String userMessage, Long projectId) {
        Long userId = authUtil.getCurrentUserId();
        createChatSessionIfNotExists(projectId, userId);

        Map<String , Object> advisorParams = Map.of(
                "userId", userId,
                "projectId", projectId
        );

        StringBuilder fullResponseBuffer = new StringBuilder();
        CodeGenerationTools codeGenerationTools = new CodeGenerationTools(projectFileService,projectId);
        return chatClient.prompt()
                .system(PromptUtils.CODE_GENERATION_SYSTEM_PROMPT)
                .user(userMessage)
                .tools(codeGenerationTools)
                .advisors(
                        advisorSpec -> {
                            advisorSpec.params(advisorParams);
                            advisorSpec.advisors(fileTreeContextAdvisor);
                        }
                )
                .stream()
                .chatResponse()
                .mapNotNull(response -> {
                    if (response == null || response.getResult() == null
                            || response.getResult().getOutput() == null) {
                        log.warn("Received ChatResponse without result/output");
                        return null;
                    }

                    return response.getResult().getOutput().getText();
                })

                .filter(content -> content != null && !content.isEmpty())

                .doOnNext(content -> {
                    fullResponseBuffer.append(content);
                })

                .doOnComplete(() -> {
                    Schedulers.boundedElastic().schedule(() -> {
                        parseAndSaveFiles(fullResponseBuffer, projectId);
                    });
                })

                .doOnError(error ->
                        log.error("Error during streaming for projectId: {}", projectId, error)
                );


    }

    private void parseAndSaveFiles(StringBuilder fullResponse, Long projectId) {
        //This is the structure of response from llm
        String dummy = """
                <message>I'm going to read the files and generate the code </message>
                <file path = "src/App.jsx">
                    import App from './App.jsx'
                    ...
                </file>
                """;


        Matcher matcher = FILE_TAG_PATTERN.matcher(fullResponse);

        while(matcher.find()){
            String filePath = matcher.group(1);
            String fileContent = matcher.group(2).trim();
            projectFileService.save(projectId, filePath, fileContent);
        }
    }

    private void createChatSessionIfNotExists(Long projectId, Long userId) {

    }
}
