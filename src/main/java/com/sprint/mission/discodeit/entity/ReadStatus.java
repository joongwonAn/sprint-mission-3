package com.sprint.mission.discodeit.entity;

import lombok.Getter;

import java.io.Serializable;
import java.time.Instant;
import java.util.UUID;

@Getter
public class ReadStatus implements Serializable {

    private final UUID id;
    private final Instant createdAt;
    private Instant updatedAt;
    private Instant readAt;

    private final UUID userId;
    private final UUID channelId;

    public ReadStatus(UUID userId, UUID channelId) {
        this.userId = userId;
        this.channelId = channelId;
        this.readAt = Instant.now();

        this.id = UUID.randomUUID();
        this.createdAt = Instant.now();
        this.updatedAt = Instant.now();
    }

    public void updateReadAt(Instant newReadTime) {
        if(newReadTime.isAfter(this.readAt)) {
            this.readAt = newReadTime;
            this.updatedAt = Instant.now();
        }
    }
}
