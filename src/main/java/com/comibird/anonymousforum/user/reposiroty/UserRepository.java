package com.comibird.anonymousforum.user.reposiroty;

import com.comibird.anonymousforum.user.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    boolean existsByEmail(String email);
    Optional<User> findFirstUserByEmailOrderByIdAsc(String email);
    Optional<User> findOneById(Long id);
    Optional<User> findOneByEmail(String email);

}
