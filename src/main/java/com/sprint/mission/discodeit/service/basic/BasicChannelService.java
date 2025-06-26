package com.sprint.mission.discodeit.service.basic;

import com.sprint.mission.discodeit.dto.ChannelDto;
import com.sprint.mission.discodeit.dto.PrivateChannelCreateDto;
import com.sprint.mission.discodeit.dto.PublicChannelCreateDto;
import com.sprint.mission.discodeit.entity.Channel;
import com.sprint.mission.discodeit.entity.ChannelType;
import com.sprint.mission.discodeit.entity.ReadStatus;
import com.sprint.mission.discodeit.entity.User;
import com.sprint.mission.discodeit.mapper.ChannelMapper;
import com.sprint.mission.discodeit.repository.ChannelRepository;
import com.sprint.mission.discodeit.repository.ReadStatusRepository;
import com.sprint.mission.discodeit.repository.UserRepository;
import com.sprint.mission.discodeit.service.ChannelService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
@RequiredArgsConstructor
public class BasicChannelService implements ChannelService {
    private final ChannelRepository channelRepository;
    private final UserRepository userRepository;
    private final ReadStatusRepository readStatusRepository;

    private final ChannelMapper channelMapper;

    @Override
    public ChannelDto createPublicChannel(PublicChannelCreateDto dto) {
        Channel channel = channelMapper.toEntity(dto);
        channelRepository.save(channel);

        return channelMapper.toDto(channel);
    }

    @Override
    public ChannelDto createPrivateChannel(PrivateChannelCreateDto dto) {
        Channel channel = new Channel(ChannelType.PRIVATE, null, null);
        channelRepository.save(channel);

        for (UUID userId : dto.getUserIds()) {
            User user = userRepository.findById(userId)
                    .orElseThrow(() -> new IllegalArgumentException("Invalid user id"));

            ReadStatus readStatus = new ReadStatus(userId, channel.getId());
            readStatusRepository.save(readStatus);
        }

        return channelMapper.toDto(channel);
    }

//    @Override
//    public Channel find(UUID channelId) {
//        return channelRepository.findById(channelId)
//                        .orElseThrow(() -> new NoSuchElementException("Channel with id " + channelId + " not found"));
//    }
//
//    @Override
//    public List<Channel> findAll() {
//        return channelRepository.findAll();
//    }
//
//    @Override
//    public Channel update(UUID channelId, String newName, String newDescription) {
//        Channel channel = channelRepository.findById(channelId)
//                .orElseThrow(() -> new NoSuchElementException("Channel with id " + channelId + " not found"));
//        channel.update(newName, newDescription);
//        return channelRepository.save(channel);
//    }
//
//    @Override
//    public void delete(UUID channelId) {
//        if (!channelRepository.existsById(channelId)) {
//            throw new NoSuchElementException("Channel with id " + channelId + " not found");
//        }
//        channelRepository.deleteById(channelId);
//    }
}
