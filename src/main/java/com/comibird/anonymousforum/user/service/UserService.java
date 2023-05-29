package com.comibird.anonymousforum.user.service;

import com.comibird.anonymousforum.user.dto.request.UserCreateRequestDTO;
import com.comibird.anonymousforum.user.dto.response.UserResponseDTO;
import com.comibird.anonymousforum.user.domain.User;
import com.comibird.anonymousforum.user.exception.AlreadyExistEmailException;
import com.comibird.anonymousforum.user.exception.UserNotFoundException;
import com.comibird.anonymousforum.user.reposiroty.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Slf4j
@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;

    @Transactional
    public void save(UserCreateRequestDTO requestDTO) {
        checkExistingEmail(requestDTO.getEmail());
        User user = requestDTO.toEntity();
        userRepository.save(user);
    }

    @Transactional(readOnly = true)
    public UserResponseDTO getUser(String email) {
        return UserResponseDTO.from(userRepository.findOneByEmail(email).orElseThrow());
    }

    @Transactional(readOnly = true)
    public void checkExistingEmail(String email) {
        if (userRepository.existsByEmail(email)) {
            throw new AlreadyExistEmailException();
        }
    }
}
