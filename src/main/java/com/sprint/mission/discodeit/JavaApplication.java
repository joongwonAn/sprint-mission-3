package com.sprint.mission.discodeit;

import com.sprint.mission.discodeit.dto.BinaryContentCreateDto;
import com.sprint.mission.discodeit.dto.UserUpdateDto;
import com.sprint.mission.discodeit.entity.BinaryContentType;
import com.sprint.mission.discodeit.dto.UserCreateDto;
import com.sprint.mission.discodeit.dto.UserStatusDto;
import com.sprint.mission.discodeit.entity.User;
import com.sprint.mission.discodeit.mapper.BinaryContentMapper;
import com.sprint.mission.discodeit.mapper.UserMapper;
import com.sprint.mission.discodeit.repository.*;
import com.sprint.mission.discodeit.repository.file.*;
import com.sprint.mission.discodeit.service.UserService;
import com.sprint.mission.discodeit.service.basic.BasicUserService;

import java.nio.charset.StandardCharsets;
import java.util.List;
import java.util.UUID;

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
                binaryContentRepository,
                userMapper,
                binaryContentMapper
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
                binaryContentDto
        );

        // create TEST
        User createdUser = userService.create(userCreateDto);
        UUID userId = createdUser.getId();
        System.out.println("\n[생성 완료] ID: " + userId);
        System.out.println("[프로필 이미지 ID] " + createdUser.getProfileImageId());

        // find TEST
        UserStatusDto foundUser = userService.find(userId);
        System.out.println("\n[find 결과]");
        System.out.println("ID: " + foundUser.getId());
        System.out.println("Username: " + foundUser.getUsername());
        System.out.println("Email: " + foundUser.getEmail());
        System.out.println("ProfileImageId: " + foundUser.getProfileImageId());

        // findAll TEST
        List<UserStatusDto> allUsers = userService.findAll();
        System.out.println("\n[findAll 결과]");
        for (UserStatusDto dto : allUsers) {
            System.out.println("→ ID: " + dto.getId() + ", Username: " + dto.getUsername()
                    + ", Email: " + dto.getEmail()
                    + ", Online: " + userService.isOnline(dto));
        }

        // update TEST
        UserUpdateDto updateDto = new UserUpdateDto(
                userId, // 어떤 유저를 수정할지 지정
                "buzz", // 새로운 username
                "buzz@codeit.com", // 새로운 email
                "buzz1234", // 새로운 password
                createdUser.getProfileImageId() // 기존 이미지 그대로 사용
        );
        User updatedUser = userService.update(updateDto);

        System.out.println("\n[update 결과]");
        System.out.println("ID: " + updatedUser.getId());
        System.out.println("Username: " + updatedUser.getUsername());
        System.out.println("Email: " + updatedUser.getEmail());
        System.out.println("ProfileImageId: " + updatedUser.getProfileImageId());

        // delete TEST
        userService.delete(userId);
        List<UserStatusDto> allUsers2 = userService.findAll();
        System.out.println("\n[After delete : findAll 결과]");
        for (UserStatusDto dto : allUsers2) {
            System.out.println("→ ID: " + dto.getId() + ", Username: " + dto.getUsername()
                    + ", Email: " + dto.getEmail()
                    + ", Online: " + userService.isOnline(dto));
        }
    }
}
