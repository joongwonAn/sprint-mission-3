package com.sprint.mission.discodeit.entity;

import lombok.Getter;

import java.io.Serializable;
import java.time.Instant;
import java.util.List;
import java.util.UUID;

@Getter
public class Message implements Serializable {
    private static final long serialVersionUID = 1L;

    private UUID id;
    private Instant updatedAt;
    private Instant createdAt;
    //
    private String content;
    //
    private UUID channelId;
    private UUID authorId;
    private List<UUID> attachmentId;

    public Message(String content, UUID channelId, UUID authorId) {
        this.id = UUID.randomUUID();
        this.createdAt = Instant.now();
        //
        this.content = content;
        this.channelId = channelId;
        this.authorId = authorId;
    }

    public void update(String newContent) {
        boolean anyValueUpdated = false;
        if (newContent != null && !newContent.equals(this.content)) {
            this.content = newContent;
            anyValueUpdated = true;
        }

        if (anyValueUpdated) {
            this.updatedAt = Instant.now();
        }
    }
}
