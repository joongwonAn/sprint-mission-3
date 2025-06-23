package com.sprint.mission.discodeit.mapper;

import com.sprint.mission.discodeit.dto.UserCreateDto;
import com.sprint.mission.discodeit.entity.User;
import org.springframework.stereotype.Component;

import java.util.UUID;

@Component
public class UserMapper {

    public User toEntity(UserCreateDto dto) {

        return new User(
                dto.getUsername(),
                dto.getEmail(),
                dto.getPassword(),
                null
        );
    }
}
