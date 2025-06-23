package com.sprint.mission.discodeit.entity;

import lombok.Getter;

import java.time.Instant;
import java.util.UUID;

@Getter
public class ReadStatus {

    private UUID id;
    private Instant createdAt;
    private Instant updatedAt;
    //
    private UUID userId;
    private UUID channelId;

    public ReadStatus(UUID userId, UUID channelId) {
        this.userId = userId;
        this.channelId = channelId;

        this.id = UUID.randomUUID();
        this.createdAt = Instant.now();
        this.updatedAt = Instant.now();
    }

    public void refreshStatus() {

        this.updatedAt = Instant.now();
    }

    public Instant getLastReadTime() {
        return this.updatedAt;
    }
}
