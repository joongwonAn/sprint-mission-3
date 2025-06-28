package com.sprint.mission.discodeit.service.basic;

import com.sprint.mission.discodeit.dto.ReadStatusCreateDto;
import com.sprint.mission.discodeit.dto.ReadStatusResponseDto;
import com.sprint.mission.discodeit.entity.Channel;
import com.sprint.mission.discodeit.entity.ReadStatus;
import com.sprint.mission.discodeit.entity.User;
import com.sprint.mission.discodeit.mapper.ReadStatusMapper;
import com.sprint.mission.discodeit.repository.ChannelRepository;
import com.sprint.mission.discodeit.repository.ReadStatusRepository;
import com.sprint.mission.discodeit.repository.UserRepository;
import com.sprint.mission.discodeit.service.ReadStatusService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.NoSuchElementException;
import java.util.Optional;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class BasicReadStatusService implements ReadStatusService {
    UserRepository userRepository;
    ChannelRepository channelRepository;
    ReadStatusRepository readStatusRepository;

    ReadStatusMapper readStatusMapper;

    @Override
    public ReadStatusResponseDto create(ReadStatusCreateDto dto) {

        getUserOrThrow(dto.getUserId());
        getChannelOrThrow(dto.getChannelId());

        Optional<ReadStatus> existing = readStatusRepository.findByUserIdAndChannelId(dto.getUserId(), dto.getChannelId());
        if (existing.isPresent()) {
            throw new IllegalStateException("이미 존재하는 ReadStatus임");
        }

        ReadStatus readStatus = readStatusMapper.toEntity(dto);
        readStatusRepository.save(readStatus);

        return readStatusMapper.toDto(readStatus);
    }

    /*public void updateReadAt(Instant newReadTime) {
        if(newReadTime.isAfter(this.readAt)) {
            this.readAt = newReadTime;
            this.updatedAt = Instant.now();
        }
    }*/

    // 중복 제거용 private method
    private User getUserOrThrow(UUID userId) {

        return userRepository.findById(userId)
                .orElseThrow(() -> new NoSuchElementException("User with id " + userId + " not found"));
    }

    private Channel getChannelOrThrow(UUID channelId) {
        return channelRepository.findById(channelId)
                .orElseThrow(() -> new NoSuchElementException("Channel with id " + channelId + " not found"));
    }
}
