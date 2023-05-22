package com.comibird.anonymousforum.service;

import com.comibird.anonymousforum.common.exception.post.AlreadyExistEmailException;
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
        if (isExistEmail(requestDTO.getEmail())) {
            throw new AlreadyExistEmailException("이미 존재하는 이메일입니다");
        }
        
        User user = requestDTO.toEntity();
        User savedUser = userRepository.save(user);
        log.info("user saved:{}", savedUser.getId());

        UserResponseDTO responseDTO = UserResponseDTO.from(user);
        return responseDTO;
    }

    private boolean isExistEmail(String email) {
        return userRepository.existsByEmail(email);
    }
}
