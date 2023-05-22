package com.comibird.anonymousforum.user.service;

import com.comibird.anonymousforum.user.dto.request.UserCreateRequestDTO;
import com.comibird.anonymousforum.user.dto.response.UserResponseDTO;
import com.comibird.anonymousforum.user.domain.User;
import com.comibird.anonymousforum.user.exception.AlreadyExistEmailException;
import com.comibird.anonymousforum.user.reposiroty.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Slf4j
@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;

    @Transactional
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

    @Transactional(readOnly = true)
    public boolean isExistEmail(String email) {
        return userRepository.existsByEmail(email);
    }
}
