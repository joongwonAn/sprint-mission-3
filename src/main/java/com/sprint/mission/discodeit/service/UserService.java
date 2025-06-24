package com.sprint.mission.discodeit.service;

import com.sprint.mission.discodeit.dto.BinaryContentCreateDto;
import com.sprint.mission.discodeit.dto.UserCreateDto;
import com.sprint.mission.discodeit.dto.UserStatusDto;
import com.sprint.mission.discodeit.dto.UserUpdateDto;
import com.sprint.mission.discodeit.entity.BinaryContent;
import com.sprint.mission.discodeit.entity.User;

import java.util.List;
import java.util.UUID;

public interface UserService {

    User create(UserCreateDto userCreateDto);

    UserStatusDto find(UUID userId);

    List<UserStatusDto> findAll();

    User update(UserUpdateDto userUpdateDto);

    void delete(UUID userId);

    boolean isOnline(UserStatusDto userStatusDto);
}
