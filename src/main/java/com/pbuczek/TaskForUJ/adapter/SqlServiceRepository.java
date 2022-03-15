package com.pbuczek.TaskForUJ.adapter;

import com.pbuczek.TaskForUJ.entity.ServiceEntity;
import com.pbuczek.TaskForUJ.repository.ServiceRepository;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SqlServiceRepository extends ServiceRepository, JpaRepository<ServiceEntity,String> {
}
