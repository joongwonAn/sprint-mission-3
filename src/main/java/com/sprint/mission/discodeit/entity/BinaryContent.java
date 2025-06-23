package com.sprint.mission.discodeit.entity;

import lombok.Getter;

import java.time.Instant;
import java.util.UUID;

@Getter
public class BinaryContent {

    private UUID id;
    private Instant createdAt;

    private UUID userId;
    private UUID messageId;

    public BinaryContent(UUID userId, UUID messageId) {

        this.userId = userId;
        this.messageId = messageId;

        this.id = UUID.randomUUID();
        this.createdAt = Instant.now();
    }


}
