package com.sprint.mission.discodeit.service;

import com.sprint.mission.discodeit.dto.BinaryContentCreateDto;
import com.sprint.mission.discodeit.dto.UserCreateDto;
import com.sprint.mission.discodeit.dto.UserStatusDto;
import com.sprint.mission.discodeit.entity.BinaryContent;
import com.sprint.mission.discodeit.entity.User;

import java.util.List;
import java.util.UUID;

public interface UserService {

    User create(UserCreateDto userCreateDto);

    UserStatusDto find(UUID userId);

    List<UserStatusDto> findAll();

    User update(UUID userId, String newUsername, String newEmail, String newPassword);

    void delete(UUID userId);
}
