package com.pbuczek.TaskForUJ.adapter;

import com.pbuczek.TaskForUJ.entity.UserEntity;
import com.pbuczek.TaskForUJ.repository.UserRepository;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SqlUserRepository extends UserRepository, JpaRepository<UserEntity,String> {
}
