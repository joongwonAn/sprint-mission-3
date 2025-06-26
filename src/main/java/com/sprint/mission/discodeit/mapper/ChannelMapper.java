package com.sprint.mission.discodeit.mapper;

import com.sprint.mission.discodeit.dto.ChannelDto;
import com.sprint.mission.discodeit.dto.PublicChannelCreateDto;
import com.sprint.mission.discodeit.entity.Channel;
import com.sprint.mission.discodeit.entity.ChannelType;
import org.springframework.stereotype.Component;

@Component
public class ChannelMapper {
    public ChannelMapper() {
    }

    public Channel toEntity(PublicChannelCreateDto dto) {
        return new Channel(
                ChannelType.PUBLIC,
                dto.getName(),
                dto.getDescription()
        );
    }

    public ChannelDto toDto(Channel channel) {
        return new ChannelDto(
                channel.getId(),
                channel.getType(),
                channel.getName(),
                channel.getDescription()
        );
    }
}
