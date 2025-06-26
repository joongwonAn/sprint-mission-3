package com.sprint.mission.discodeit.service;

import com.sprint.mission.discodeit.dto.ChannelDto;
import com.sprint.mission.discodeit.dto.PrivateChannelCreateDto;
import com.sprint.mission.discodeit.dto.PublicChannelCreateDto;

public interface ChannelService {
    ChannelDto createPublicChannel(PublicChannelCreateDto dto);

    ChannelDto createPrivateChannel(PrivateChannelCreateDto dto);

//    Channel find(UUID channelId);
//
//    List<Channel> findAll();
//
//    Channel update(UUID channelId, String newName, String newDescription);
//
//    void delete(UUID channelId);
}
