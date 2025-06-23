package com.sprint.mission.discodeit.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class BinaryContentCreateDto {

    private byte[] bytes;
    private String fileName;
    private BinaryContentType fileType;
}
