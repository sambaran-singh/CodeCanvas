package com.sambaran.codecanvas.entity;

import com.sambaran.codecanvas.enums.MessageRole;
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
public class ChatMessage {

    Long id;
    ChatSession chatSession;
    String content;
    String toolCalls;
    Integer tokensUsed;
    Instant createdAt;
    MessageRole messageRole;
}
