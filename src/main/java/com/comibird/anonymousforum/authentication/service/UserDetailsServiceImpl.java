package com.comibird.anonymousforum.authentication.service;

import com.comibird.anonymousforum.authentication.domain.UserDetailsImpl;
import com.comibird.anonymousforum.authentication.exception.UserNotFoundException;
import com.comibird.anonymousforum.user.domain.User;
import com.comibird.anonymousforum.user.reposiroty.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class UserDetailsServiceImpl implements UserDetailsService {

    private final UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String email) {
        User user = userRepository.findFirstUserByEmailOrderByIdAsc(email).orElseThrow(() -> new UserNotFoundException());
        return new UserDetailsImpl(
                user.getEmail(),
                user.getPassword()
        );
    }

}
