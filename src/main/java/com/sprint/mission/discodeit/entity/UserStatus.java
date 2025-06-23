package com.sprint.mission.discodeit.entity;

import lombok.Getter;

import java.time.Instant;
import java.util.UUID;

@Getter
public class UserStatus {

    private UUID id;
    private Instant createdAt;
    private Instant updatedAt;
    //
    private UUID userId;

    // 생성자
    public UserStatus(UUID userId) {
        this.id = UUID.randomUUID();
        this.createdAt = Instant.now();
        this.updatedAt = Instant.now();

        this.userId = userId;
    }

    public void refreshStatus() {
        this.updatedAt = Instant.now();
    }

    // 유저가 현재 접속 중인지 판단 -> updatedAt이 현재로부터 5분 이내면 접속 중
    public boolean isOnline() {
        Instant fiveMinutesAgo = Instant.now().minusSeconds(60 * 5);
        return fiveMinutesAgo.isBefore(this.updatedAt);
    }
}
