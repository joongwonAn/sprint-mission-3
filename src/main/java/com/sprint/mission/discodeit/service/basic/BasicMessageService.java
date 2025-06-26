package com.sprint.mission.discodeit.service.basic;

import com.sprint.mission.discodeit.dto.BinaryContentCreateDto;
import com.sprint.mission.discodeit.dto.MessageCreateDto;
import com.sprint.mission.discodeit.dto.MessageResponseDto;
import com.sprint.mission.discodeit.entity.BinaryContent;
import com.sprint.mission.discodeit.entity.Message;
import com.sprint.mission.discodeit.mapper.BinaryContentMapper;
import com.sprint.mission.discodeit.mapper.MessageMapper;
import com.sprint.mission.discodeit.repository.BinaryContentRepository;
import com.sprint.mission.discodeit.repository.ChannelRepository;
import com.sprint.mission.discodeit.repository.MessageRepository;
import com.sprint.mission.discodeit.repository.UserRepository;
import com.sprint.mission.discodeit.service.MessageService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class BasicMessageService implements MessageService {

    private final MessageRepository messageRepository;
    private final ChannelRepository channelRepository;
    private final UserRepository userRepository;
    private final BinaryContentRepository binaryContentRepository;

    private final MessageMapper messageMapper;
    private final BinaryContentMapper binaryContentMapper;

    @Override
    public MessageResponseDto create(MessageCreateDto dto) {

        channelRepository.findById(dto.getChannelId())
                .orElseThrow(() -> new NoSuchElementException("Channel not found with id " + dto.getChannelId()));
        userRepository.findById(dto.getAuthorId())
                .orElseThrow(() -> new NoSuchElementException("Author not found with id " + dto.getAuthorId()));

        if(dto.getAttachmentIds() != null){
            for (UUID attachmentId : dto.getAttachmentIds()) {
                binaryContentRepository.findById(attachmentId)
                        .orElseThrow(() -> new NoSuchElementException("Attachment not found with id " + attachmentId));
            }
        }

        Message message = messageMapper.toEntity(dto);
        messageRepository.save(message);

        return messageMapper.toDto(message);
    }

//    @Override
//    public Message find(UUID messageId) {
//        return messageRepository.findById(messageId)
//                .orElseThrow(() -> new NoSuchElementException("Message with id " + messageId + " not found"));
//    }
//
//    @Override
//    public List<Message> findAll() {
//        return messageRepository.findAll();
//    }
//
//    @Override
//    public Message update(UUID messageId, String newContent) {
//        Message message = messageRepository.findById(messageId)
//                .orElseThrow(() -> new NoSuchElementException("Message with id " + messageId + " not found"));
//        message.update(newContent);
//        return messageRepository.save(message);
//    }
//
//    @Override
//    public void delete(UUID messageId) {
//        if (!messageRepository.existsById(messageId)) {
//            throw new NoSuchElementException("Message with id " + messageId + " not found");
//        }
//        messageRepository.deleteByChannelId(messageId);
//    }

    // 중복 메서드

}
