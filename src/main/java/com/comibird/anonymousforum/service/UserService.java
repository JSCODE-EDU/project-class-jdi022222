package com.comibird.anonymousforum.service;

import com.comibird.anonymousforum.domain.member.User;
import com.comibird.anonymousforum.domain.member.UserRepository;
import com.comibird.anonymousforum.dto.member.UserCreateRequestDTO;
import com.comibird.anonymousforum.dto.member.UserResponseDTO;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;

    public UserResponseDTO save(UserCreateRequestDTO requestDTO) {
        User user = requestDTO.toEntity();
        User savedUser = userRepository.save(user);
        log.info("user saved:{}", savedUser.getId());

        UserResponseDTO responseDTO = UserResponseDTO.from(user);

        return responseDTO;
    }
}
