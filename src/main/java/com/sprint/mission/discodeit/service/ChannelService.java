package com.sprint.mission.discodeit.service;

import com.sprint.mission.discodeit.dto.ChannelDto;
import com.sprint.mission.discodeit.dto.PrivateChannelCreateDto;
import com.sprint.mission.discodeit.dto.PublicChannelCreateDto;

import java.util.UUID;

public interface ChannelService {
    ChannelDto createPublicChannel(PublicChannelCreateDto dto);

    ChannelDto createPrivateChannel(PrivateChannelCreateDto dto);

    ChannelDto find(UUID channelId);

//    Channel find(UUID channelId);
//
//    List<Channel> findAll();
//
//    Channel update(UUID channelId, String newName, String newDescription);
//
//    void delete(UUID channelId);
}
