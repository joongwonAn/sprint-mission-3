package com.sprint.mission.discodeit.service;

import com.sprint.mission.discodeit.dto.UserLoginDto;
import com.sprint.mission.discodeit.dto.UserStatusDto;

public interface AuthService {

    UserStatusDto login(UserLoginDto dto);
}
