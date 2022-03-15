package com.pbuczek.TaskForUJ.repository;

import com.pbuczek.TaskForUJ.entity.UserEntity;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Map;
import java.util.Optional;

public interface UserRepository {
    boolean existsById(String id);

    boolean existsByLogin(String id);

      List<UserEntity> findAll();

    Optional<UserEntity> findById(String id);

    Optional<UserEntity> findByLogin(String id);

    UserEntity save(UserEntity ue);

}
