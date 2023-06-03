package com.comibird.anonymousforum.auth.repository;

import com.comibird.anonymousforum.auth.domain.AccessToken;
import com.comibird.anonymousforum.auth.domain.RefreshToken;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AccessTokenRepository extends CrudRepository<AccessToken, String> {

}
