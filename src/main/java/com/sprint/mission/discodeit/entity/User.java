package com.sprint.mission.discodeit.entity;

import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.time.Instant;
import java.util.UUID;

@Getter
@Setter
public class User implements Serializable {

    private static final long serialVersionUID = 1L;

    private UUID id;
    private Instant createdAt;
    private Instant updatedAt;
    //
    private String username;
    private String email;
    private String password;
    @Setter
    private UUID profileImageId;

    public User(String username, String email, String password, UUID profileImageId) {
        this.id = UUID.randomUUID();
        this.createdAt = Instant.now();
        //
        this.username = username;
        this.email = email;
        this.password = password;
        //
        this.profileImageId = profileImageId;
    }
}
