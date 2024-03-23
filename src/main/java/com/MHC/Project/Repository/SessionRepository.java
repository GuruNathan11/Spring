package com.MHC.Project.Repository;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.MHC.Project.Model.Session;

public interface SessionRepository extends MongoRepository<Session, String> {

    Optional<Session> findBySessionId(String sessionId);

    List<Session> findByUsername(String username);

    void deleteByExpireTimeBefore(LocalDateTime expireTime);


}