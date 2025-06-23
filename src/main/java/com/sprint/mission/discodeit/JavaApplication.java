package com.sprint.mission.discodeit;

import com.sprint.mission.discodeit.dto.BinaryContentCreateDto;
import com.sprint.mission.discodeit.dto.BinaryContentType;
import com.sprint.mission.discodeit.dto.UserCreateDto;
import com.sprint.mission.discodeit.entity.User;
import com.sprint.mission.discodeit.mapper.BinaryContentMapper;
import com.sprint.mission.discodeit.mapper.UserMapper;
import com.sprint.mission.discodeit.repository.*;
import com.sprint.mission.discodeit.repository.file.*;
import com.sprint.mission.discodeit.service.UserService;
import com.sprint.mission.discodeit.service.basic.BasicUserService;

import java.nio.charset.StandardCharsets;

public class JavaApplication {

    public static void main(String[] args) {

        // 매퍼 및 리포지토리 초기화
        UserMapper userMapper = new UserMapper();
        BinaryContentMapper binaryContentMapper = new BinaryContentMapper();
        UserRepository userRepository = new FileUserRepository();
        UserStatusRepository userStatusRepository = new FileUserStatusRepository();
        BinaryContentRepository binaryContentRepository = new FileBinaryContentRepository();

        // 서비스 초기화
        UserService userService = new BasicUserService(
                userRepository,
                userStatusRepository,
                userMapper,
                binaryContentMapper,
                binaryContentRepository
        );

        // 더미 프로필 이미지 생성
        byte[] dummyBytes = "hello image file content".getBytes(StandardCharsets.UTF_8);
        BinaryContentCreateDto binaryContentDto = new BinaryContentCreateDto(
                dummyBytes,
                "profile.jpg",
                BinaryContentType.USER_PROFILE_IMAGE
        );

        // 유저 생성 DTO
        UserCreateDto userCreateDto = new UserCreateDto(
                "woody",
                "woody@codeit.com",
                "woody1234",
                binaryContentDto // 없애고 싶으면 null 넣기
        );

        // 생성 테스트
        User user = userService.create(userCreateDto);
        System.out.println("[생성 완료] ID: " + user.getId());
        System.out.println("[프로필 이미지 ID] " + user.getProfileImageId());
    }
}
