package com.sprint.mission.discodeit.service;

import com.sprint.mission.discodeit.dto.ReadStatusCreateDto;
import com.sprint.mission.discodeit.dto.ReadStatusResponseDto;

import java.util.UUID;

public interface ReadStatusService {

    ReadStatusResponseDto create(ReadStatusCreateDto readStatusCreateDto);

    ReadStatusResponseDto find(UUID id);
}
