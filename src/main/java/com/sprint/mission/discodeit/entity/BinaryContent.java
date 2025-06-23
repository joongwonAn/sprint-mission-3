package com.sprint.mission.discodeit.entity;

import lombok.Getter;

import java.time.Instant;
import java.util.UUID;

@Getter
public class BinaryContent {

    private UUID id;
    private Instant createdAt;

    private  UUID userId;
    private  UUID messageId;

    private byte[] bytes;

    public BinaryContent(UUID userId, UUID messageId, byte[] bytes) {
        this.userId = userId;
        this.messageId = messageId;

        this.id = UUID.randomUUID();
        this.createdAt = Instant.now();

        this.bytes = bytes;
    }
}
