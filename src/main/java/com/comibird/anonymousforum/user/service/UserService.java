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
    public void save(UserCreateRequestDTO requestDTO) {
        validateEmail(requestDTO.getEmail());
        User user = requestDTO.toEntity();
        userRepository.save(user);
    }

    @Transactional(readOnly = true)
    public void validateEmail(String email) {
        if (userRepository.existsByEmail(email)) {
            throw new AlreadyExistEmailException("이미 존재하는 이메일입니다");
        }
    }
}
