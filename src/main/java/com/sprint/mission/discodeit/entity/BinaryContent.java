package com.sprint.mission.discodeit.entity;

import lombok.Getter;

import java.io.Serializable;
import java.time.Instant;
import java.util.UUID;

@Getter
public class BinaryContent implements Serializable {

    private UUID id;
    private Instant createdAt;

    private  UUID userId;
    private  UUID messageId;

    private byte[] bytes;
    private String fileName;
    private BinaryContentType fileType;

    public BinaryContent(UUID userId, UUID messageId, byte[] bytes, String fileName, BinaryContentType fileType) {
        this.userId = userId;
        this.messageId = messageId;

        this.id = UUID.randomUUID();
        this.createdAt = Instant.now();

        this.bytes = bytes;
        this.fileName = fileName;
        this.fileType = fileType;
    }
}
