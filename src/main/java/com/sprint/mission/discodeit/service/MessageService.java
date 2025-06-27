package com.sprint.mission.discodeit.service;

import com.sprint.mission.discodeit.dto.MessageCreateDto;
import com.sprint.mission.discodeit.dto.MessageResponseDto;
import com.sprint.mission.discodeit.entity.Message;

import java.util.List;
import java.util.UUID;

public interface MessageService {

    MessageResponseDto create(MessageCreateDto messageCreateDto);

    List<MessageResponseDto> findByChannelId(UUID channelId);

    Message update(UUID messageId, String newContent);

    void delete(UUID messageId);
}
